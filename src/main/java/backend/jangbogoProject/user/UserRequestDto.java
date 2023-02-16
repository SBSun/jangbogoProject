package backend.jangbogoProject.user;

import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserRequestDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SignUp {
        private String email;
        private String password;
        private String name;
        private String address;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Login {
        private String email;
        private String password;

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(email, password);
        }
    }

    @Getter
    @Setter
    public static class Reissue {
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
