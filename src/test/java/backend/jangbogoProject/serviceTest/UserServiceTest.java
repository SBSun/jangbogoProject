package backend.jangbogoProject.serviceTest;

import backend.jangbogoProject.user.*;
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
                .id("test@test.test")
                .password("test Password")
                .name("test Name")
                .address("test Address")
                .build();
    }

    private UserResponseDto.Info userResponse(){
        return UserResponseDto.Info.builder()
                .user_id("test@test.test")
                .password("test Password")
                .name("test Name")
                .address("test Address")
                .build();
    }

    @DisplayName("회원 가입")
    @Test
    void signUp(){
        // given
        UserRequestDto.SignUp request = signUpRequest();
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = User.builder()
                .id(request.getId())
                .password(encoder.encode(request.getPassword()))
                .name(request.getName())
                .address(request.getAddress())
                .authority(Authority.USER.getValue())
                .build();

        doReturn(user).when(userRepository)
                .save(any(User.class));

        // when
        UserResponseDto.Info info = userService.signUp(request);

        // then
        assertThat(user.getId()).isEqualTo(request.getId());
        assertThat(encoder.matches(request.getPassword(), info.getPassword())).isTrue();

        // verify
        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode(any(String.class));
    }

    @Test
    void 아이디_중복확인(){
        // given
        UserRequestDto.SignUp signUp1 = signUpRequest();
        UserRequestDto.SignUp signUp2 = signUpRequest();

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = User.builder()
                .id(signUp1.getId())
                .password(encoder.encode(signUp1.getPassword()))
                .name(signUp1.getName())
                .address(signUp1.getAddress())
                .authority(Authority.USER.getValue())
                .build();

        doReturn(user).when(userRepository)
                .save(any(User.class));

        // when
        userService.signUp(signUp1);

        // then
        assertThat(userService.checkId(signUp2.getId())).isFalse();
    }
}
