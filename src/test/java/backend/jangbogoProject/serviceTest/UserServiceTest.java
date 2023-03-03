package backend.jangbogoProject.serviceTest;

import backend.jangbogoProject.constant.Authority;
import backend.jangbogoProject.dto.UserRequestDto;
import backend.jangbogoProject.dto.UserResponseDto;
import backend.jangbogoProject.entity.User;
import backend.jangbogoProject.repository.UserRepository;
import backend.jangbogoProject.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private PasswordEncoder passwordEncoder;

    private UserRequestDto.SignUp signUpRequest(){
        return UserRequestDto.SignUp.builder()
                .email("test@test.test")
                .password("test Password")
                .name("test Name")
                .build();
    }

    private UserResponseDto.Info userResponse(){
        return UserResponseDto.Info.builder()
                .email("test@test.test")
                .password("test Password")
                .name("test Name")
                .build();
    }

    @DisplayName("회원 가입")
    @Test
    void signUp(){
        // given
        UserRequestDto.SignUp request = signUpRequest();
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .name(request.getName())
                .authority(Authority.USER.getValue())
                .build();

        doReturn(user).when(userRepository)
                .save(any(User.class));

        // when
        Long createdUserId = userService.signUp(request);

        // then
        assertThat(user.getEmail()).isEqualTo(request.getEmail());

        // verify
        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode(any(String.class));
    }
    /*
    @Test
    void 이메일_중복확인(){
        // given
        UserRequestDto.SignUp signUp1 = signUpRequest();
        UserRequestDto.SignUp signUp2 = signUpRequest();

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = User.builder()
                .email(signUp1.getEmail())
                .password(encoder.encode(signUp1.getPassword()))
                .name(signUp1.getName())
                .authority(Authority.USER.getValue())
                .build();

        doReturn(user).when(userRepository)
                .save(any(User.class));

        // when
        userService.signUp(signUp1);

        // then
        assertThat(userService.checkEmail(signUp2.getEmail())).isFalse();
    }*/
}
