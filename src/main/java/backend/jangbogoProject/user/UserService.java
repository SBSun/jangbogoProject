package backend.jangbogoProject.user;

import backend.jangbogoProject.dto.BasicResponse;
import backend.jangbogoProject.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

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
    public UserDto.TokenInfo login(UserDto.LoginRequest loginInfo){
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginInfo.getId(), loginInfo.getPassword());

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        UserDto.TokenInfo tokenInfo = jwtTokenProvider.createToken(authentication);

        return tokenInfo;
    }

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
}
