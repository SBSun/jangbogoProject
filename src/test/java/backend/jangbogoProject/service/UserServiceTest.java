package backend.jangbogoProject.service;

import backend.jangbogoProject.constant.Authority;
import backend.jangbogoProject.constant.LoginType;
import backend.jangbogoProject.dto.UserRequestDto;
import backend.jangbogoProject.dto.UserResponseDto;
import backend.jangbogoProject.entity.User;
import backend.jangbogoProject.jwt.JwtTokenProvider;
import backend.jangbogoProject.repository.ReviewRepository;
import backend.jangbogoProject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private RedisTemplate redisTemplate;
    @Mock
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, redisTemplate, authenticationManagerBuilder, jwtTokenProvider, passwordEncoder, reviewRepository);
    }

    @Test
    public void 유저_등록_테스트(){
        // given
        UserRequestDto.SignUp dto = SignUpRequest();

        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .loginType(LoginType.Form.name())
                .authority(Authority.USER.getValue())
                .build();

        // stub
        // 반환될 값을 미리 지정한다.
        when(userRepository.save(any(User.class))).thenReturn(user);

        // when
        UserResponseDto.Info userInfoDto = userService.signUp(dto);

        // then
        assertThat(dto.getEmail()).isEqualTo(userInfoDto.getEmail());
        assertThat(dto.getName()).isEqualTo(userInfoDto.getName());
    }

    @Test
    public void 유저_조회_테스트(){
        // given
        String email = "Test Email";

        // stub
        User user = CreateUser();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // when
        UserResponseDto.Info userInfoDto = userService.findUserInfo(email);

        // then
        assertThat(userInfoDto.getEmail()).isEqualTo(email);
    }

    @Test
    public void 유저_삭제_테스트(){
        
    }

    @Test
    public void 유저_수정_테스트(){
        // given
        UserRequestDto.Edit dto = new UserRequestDto.Edit("Test Email", "Edit Password", "Edit Name");
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        // stub
        User user = CreateUser();

        when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(dto.getPassword())).thenReturn(encoder.encode(dto.getPassword()));

        // when
        UserResponseDto.Info userInfoDto = userService.editUser(dto);

        // then

        assertTrue(encoder.matches(dto.getPassword(), userInfoDto.getPassword()));
        assertThat(userInfoDto.getName()).isEqualTo(dto.getName());
    }

    private UserRequestDto.SignUp SignUpRequest(){
        return UserRequestDto.SignUp.builder()
                .email("Test Email")
                .name("Test Name")
                .password("Test Password")
                .build();
    }

    private User CreateUser(){
        String email = "Test Email";
        String password = "Test Password";
        String name = "Test Name";
        String loginType = "Test LoginType";
        String authority = "Test Authority";
        return  User.builder()
                .email(email)
                .password(password)
                .name(name)
                .loginType(loginType)
                .authority(authority)
                .build();
    }
}
