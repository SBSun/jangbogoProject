package backend.jangbogoProject.dto;

import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.*;

public class UserRequestDto {

    @Getter
    @NoArgsConstructor
    public static class Check{
        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        @Email(message = "이메일 형식에 맞지 않습니다.")
        private String email;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SignUp {
        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        @Email(message = "이메일 형식에 맞지 않습니다.")
        private String email;
        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        private String password;
        @NotBlank(message = "이름은 필수 입력 값입니다.")
        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Login {

        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        @Email(message = "이메일 형식에 맞지 않습니다.")
        private String email;
        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        private String password;

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(email, password);
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Edit{
        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        private String password;
        @NotBlank(message = "이름은 필수 입력 값입니다.")
        private String name;
    }

    @Getter
    @Setter
    public static class Reissue {
        @NotBlank(message = "AccessToken은 필수 입력 값입니다.")
        private String accessToken;
        private String refreshToken;
    }

    @Getter
    @Setter
    public static class Logout {
        private String accessToken;
        private String refreshToken;
    }
}
