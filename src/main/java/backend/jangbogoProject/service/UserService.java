package backend.jangbogoProject.service;

import backend.jangbogoProject.dto.ResponseDTO;
import backend.jangbogoProject.dto.UserRequestDto;
import backend.jangbogoProject.dto.UserResponseDto;
import backend.jangbogoProject.entity.User;
import backend.jangbogoProject.jwt.JwtTokenProvider;
import backend.jangbogoProject.repository.ReviewRepository;
import backend.jangbogoProject.constant.Authority;
import backend.jangbogoProject.repository.UserRepository;
import backend.jangbogoProject.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static backend.jangbogoProject.jwt.JwtAuthenticationFilter.AUTHORIZATION_HEADER;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService{
    private final UserRepository userRepository;
    private final RedisTemplate redisTemplate;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final ReviewRepository reviewRepository;

    @Transactional
    public Long signUp(UserRequestDto.SignUp signUp) {
        if(userRepository.existsByEmail(signUp.getEmail())){
            throw new IllegalArgumentException("해당 이메일은 이미 가입되어 있는 이메일입니다.");
        }

        User user = User.builder()
                .email(signUp.getEmail())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .name(signUp.getName())
                .authority(Authority.USER.getValue())
                .build();

        return userRepository.save(user).getId();
    }

    public UserResponseDto.Info getUserInfo(String email){
        UserResponseDto.Info info = UserResponseDto.Info.of(userRepository.findByEmail(email).get());

        return info;
    }

    public List<UserResponseDto.Info> findAllUser(){
        List<User> userList = userRepository.findAll();

        List<UserResponseDto.Info> infoList =
                userList.stream()
                        .map(user -> UserResponseDto.Info.of(user))
                        .collect(Collectors.toList());

        return infoList;
    }

    @Transactional
    // 트랜잭션 안에서 데이터베이스의 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태가 된다.
    //이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 변경된 데이터를 데이터베이스에 반영해준다.
    public void editUser(UserRequestDto.Edit edit){
        String loginUserEmail = SecurityUtil.getCurrentUserEmail().get();
        System.out.println("loginUserEmail : " + loginUserEmail);

        if(loginUserEmail.equals("anonymousUser"))
            throw new RuntimeException("로그인한 유저가 아닙니다.");

        User user = userRepository.findByEmail(loginUserEmail).get();

        String encPassword = passwordEncoder.encode(edit.getPassword());

        user.update(encPassword, edit.getName());
    }

    @Transactional
    public Long deleteUser(){
        String loginUserEmail = SecurityUtil.getCurrentUserEmail().get();

        if(loginUserEmail.equals("anonymousUser"))
            throw new RuntimeException("로그인한 유저가 아닙니다.");

        reviewRepository.deleteByUserEmail(loginUserEmail);
        return userRepository.deleteByEmail(loginUserEmail);
    }

    public UserResponseDto.LoginInfo login(UserRequestDto.Login login){
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.createToken(authentication);

        // 4. RefreshToken Redis 저장 (expirationTime 설정을 통해 자동 삭제 처리)
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        User loginUser = userRepository.findByEmail(login.getEmail()).get();

        return new UserResponseDto.LoginInfo(loginUser.getName(), tokenInfo);
    }

    public Optional<String> getLoginUserEmail(){
        return SecurityUtil.getCurrentUserEmail();
    }

    // 토큰 재발급
    public UserResponseDto.TokenInfo reissue(UserRequestDto.Reissue reissue) {
        // 1. Refresh Token 검증
        if (!jwtTokenProvider.validateToken(reissue.getRefreshToken())) {
            throw new IllegalArgumentException("Refresh Token 정보가 유효하지 않습니다.");
        }

        // 2. Access Token 에서 User email 를 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(reissue.getAccessToken());

        // 3. Redis 에서 User email 을 기반으로 저장된 Refresh Token 값을 가져옵니다.
        String refreshToken = (String)redisTemplate.opsForValue().get("RT:" + authentication.getName());
        // 로그아웃되어 Redis에 Refresh Token이 존재하지 않는 경우
        if(ObjectUtils.isEmpty(refreshToken)){
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        if(!refreshToken.equals(reissue.getRefreshToken())) {
            throw new IllegalArgumentException("Refresh Token 정보가 일치하지 않습니다.");
        }

        // 4. 새로운 토큰 생성
        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.createToken(authentication);

        // 5. RefreshToken Redis 업데이트
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return tokenInfo;
    }

    public String logout(HttpServletRequest request) {
        String accessToken = request.getHeader(AUTHORIZATION_HEADER).substring(7); // "Bearer " 이후의 ACCESS_TOKEN 문자열 반환

        // 1. Access Token 검증
        if (!jwtTokenProvider.validateToken(accessToken)) {
            throw new IllegalArgumentException("로그아웃 : 유효하지 않은 토큰입니다.");
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

        // 3. Redis 에서 해당 User email 로 저장된 Refresh Token 이 있는지 여부를 확인 후 있을 경우 삭제합니다.
        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            // Refresh Token 삭제
            redisTemplate.delete("RT:" + authentication.getName());
        }

        // 4. 해당 Access Token 유효시간 가지고 와서 BlackList 로 저장하기
        Long expiration = jwtTokenProvider.getExpiration(accessToken);
        redisTemplate.opsForValue()
                .set(accessToken, "logout", expiration, TimeUnit.MILLISECONDS);

        return "로그아웃 되었습니다.";
    }

    public boolean checkEmail(String email){
        return !userRepository.existsByEmail(email);
    }
}
