package backend.jangbogoProject.user;

import backend.jangbogoProject.dto.BasicResponse;
import backend.jangbogoProject.jwt.JwtTokenProvider;
import backend.jangbogoProject.jwt.RefreshToken;
import backend.jangbogoProject.jwt.RefreshTokenRepository;
import backend.jangbogoProject.jwt.TokenDto;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public UserDto.Info findById(String id){

        if(userRepository.existsById(id)){
            User user = userRepository.findById(id).get();

            UserDto.Info info = UserDto.Info.builder()
                    .id(user.getId())
                    .password(user.getPassword())
                    .name(user.getName())
                    .address(user.getAddress())
                    .build();

            return info;
        }

        return UserDto.Info.builder().build();
    }

    @Transactional
    // 트랜잭션 안에서 데이터베이스의 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태가 된다.
    //이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 변경된 데이터를 데이터베이스에 반영해준다.
    public void updateInfo(UserDto.Info info){
        User user = userRepository.findByUserId(info.getId());

        if(user == null)
            new IllegalArgumentException("해당 회원은 존재하지 않습니다.");

        String encPassword = passwordEncoder.encode(info.getPassword());

        user.update(encPassword, info.getName(), info.getAddress());
    }

    //회원가입
    public BasicResponse signUp(UserDto.SignUpRequest signUpDto, Authority authority)
    {
        if(userRepository.existsById(signUpDto.getId())){
            BasicResponse basicResponse = BasicResponse.builder()
                    .state(HttpStatus.BAD_REQUEST.value())
                    .message("이미 회원가입된 이메일입니다.")
                    .build();
            return basicResponse;
        }

        User user = User.builder()
                .id(signUpDto.getId())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .name(signUpDto.getName())
                .address(signUpDto.getAddress())
                .authority(authority.getValue())
                .build();

        userRepository.save(user);

        BasicResponse basicResponse = BasicResponse.builder()
                .state(HttpStatus.OK.value())
                .message("회원가입이 완료되었습니다.")
                .build();
        return basicResponse;
    }

    @Transactional
    public TokenDto login(UserDto.LoginRequest loginInfo){
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginInfo.getId(), loginInfo.getPassword());

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        String accessToken = jwtTokenProvider.createAccessToken(authentication);
        RefreshToken refreshToken = saveRefreshToken(authentication);

        return TokenDto.of(accessToken, refreshToken.getRefreshToken());
    }

    private RefreshToken saveRefreshToken(Authentication authentication) {
        return refreshTokenRepository.save(RefreshToken.createRefreshToken(authentication.getName(),
                jwtTokenProvider.createRefreshToken(authentication), JwtTokenProvider.REFRESH_TOKEN_EXPIRE_TIME));
    }

    private String resolveToken(String token) {
        return token.substring(7);
    }

    public TokenDto reissue(String refreshToken) {
        refreshToken = resolveToken(refreshToken);
        String username = getCurrentUsername();
        RefreshToken redisRefreshToken = refreshTokenRepository.findById(username).orElseThrow(NoSuchElementException::new);

        if (refreshToken.equals(redisRefreshToken.getRefreshToken())) {
            return reissueRefreshToken(refreshToken, username);
        }
        throw new IllegalArgumentException("토큰이 일치하지 않습니다.");
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return principal.getUsername();
    }

    private TokenDto reissueRefreshToken(String refreshToken, String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (lessThanReissueExpirationTimesLeft(refreshToken)) {
            String accessToken = jwtTokenProvider.createAccessToken(authentication);
            return TokenDto.of(accessToken, saveRefreshToken(authentication).getRefreshToken());
        }
        return TokenDto.of(jwtTokenProvider.createAccessToken(authentication), refreshToken);
    }

    private boolean lessThanReissueExpirationTimesLeft(String refreshToken) {
        return jwtTokenProvider.getRemainMilliSeconds(refreshToken) < jwtTokenProvider.REISSUE_EXPIRATION_TIME;
    }

}
