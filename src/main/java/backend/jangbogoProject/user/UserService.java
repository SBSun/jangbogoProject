package backend.jangbogoProject.user;

import backend.jangbogoProject.dto.BasicResponse;
import backend.jangbogoProject.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final RedisTemplate redisTemplate;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    public UserResponseDto.Info signUp(UserRequestDto.SignUp signUp)
    {
        if(userRepository.existsById(signUp.getId())){
            return null;
        }

        User user = User.builder()
                .id(signUp.getId())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .name(signUp.getName())
                .address(signUp.getAddress())
                .authority(Authority.USER.getValue())
                .build();

        return UserResponseDto.Info.of(userRepository.save(user));
    }

    public UserInfo getUserInfo(String id){

        UserInfo info = userRepository.findByUserId(id);

        return info;
    }

    public UserResponseDto.InfoList findALlUser(){
        List<UserInfo> infoList = userRepository.findAllUser();

        int state;
        String message;

        if(infoList.isEmpty()){
            state = 404;
            message = "데이터가 존재하지 않습니다.";
        }else{
            state = 200;
            message = "데이터 반환 성공";
        }

        BasicResponse basicResponse = new BasicResponse(state, message);

        return new UserResponseDto.InfoList(infoList, basicResponse);
    }

    @Transactional
    // 트랜잭션 안에서 데이터베이스의 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태가 된다.
    //이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 변경된 데이터를 데이터베이스에 반영해준다.
    public void updateUserInfo(UserInfo info){
        User user = userRepository.findById(info.getUser_Id()).get();

        if(user == null)
            new IllegalArgumentException("해당 회원은 존재하지 않습니다.");

        String encPassword = passwordEncoder.encode(info.getPassword());

        user.update(encPassword, info.getName(), info.getAddress());
    }



    public UserResponseDto.TokenInfo login(UserRequestDto.Login login){
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getId(), login.getPassword());

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.createToken(authentication);

        // 4. RefreshToken Redis 저장 (expirationTime 설정을 통해 자동 삭제 처리)
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return tokenInfo;
    }

    // 토큰 재발급
    public UserResponseDto.TokenInfo reissue(UserRequestDto.Reissue reissue) {
        // 1. Refresh Token 검증
        if (!jwtTokenProvider.validateToken(reissue.getRefreshToken())) {
            BasicResponse basicResponse = new BasicResponse(HttpStatus.BAD_REQUEST.value(), "Refresh Token 정보가 유효하지 않습니다.");
            return new UserResponseDto.TokenInfo(basicResponse);
        }

        // 2. Access Token 에서 User email 를 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(reissue.getAccessToken());

        // 3. Redis 에서 User email 을 기반으로 저장된 Refresh Token 값을 가져옵니다.
        String refreshToken = (String)redisTemplate.opsForValue().get("RT:" + authentication.getName());
        // 로그아웃되어 Redis에 Refresh Token이 존재하지 않는 경우
        if(ObjectUtils.isEmpty(refreshToken)){
            BasicResponse basicResponse = new BasicResponse(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다.");
            return new UserResponseDto.TokenInfo(basicResponse);
        }
        if(!refreshToken.equals(reissue.getRefreshToken())) {
            BasicResponse basicResponse = new BasicResponse(HttpStatus.BAD_REQUEST.value(), "Refresh Token 정보가 일치하지 않습니다.");
            return new UserResponseDto.TokenInfo(basicResponse);
        }

        // 4. 새로운 토큰 생성
        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.createToken(authentication);

        // 5. RefreshToken Redis 업데이트
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return tokenInfo;
    }

    public BasicResponse logout(UserRequestDto.Logout logout) {
        // 1. Access Token 검증
        if (!jwtTokenProvider.validateToken(logout.getAccessToken())) {
            return new BasicResponse(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다.");
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(logout.getAccessToken());

        // 3. Redis 에서 해당 User email 로 저장된 Refresh Token 이 있는지 여부를 확인 후 있을 경우 삭제합니다.
        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            // Refresh Token 삭제
            redisTemplate.delete("RT:" + authentication.getName());
        }

        // 4. 해당 Access Token 유효시간 가지고 와서 BlackList 로 저장하기
        Long expiration = jwtTokenProvider.getExpiration(logout.getAccessToken());
        redisTemplate.opsForValue()
                .set(logout.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);

        return new BasicResponse(HttpStatus.OK.value(), "로그아웃 되었습니다.");
    }
}
