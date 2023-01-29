package backend.jangbogoProject.user;

import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserRequestDto {

    @Getter
    @Setter
    @Builder
    public static class SignUp {
        private String id;
        private String password;
        private String name;
        private String address;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Login {
        private String id;
        private String password;

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(id, password);
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
