package backend.jangbogoProject.user;

import backend.jangbogoProject.dto.BasicResponse;
import lombok.*;

public class UserDto {
    @Getter
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Info {
        private String id;
        private String password;
        private String name;
        private String address;

        public User toEntity() {
            User build = User.builder()
                    .id(id)
                    .password(password)
                    .name(name)
                    .address(address)
                    .build();
            return build;
        }
    }

    @Getter
    @Setter
    public static class SignUpRequest{
        private String id;
        private String password;
        private String name;
        private String address;
    }

    @Getter
    @Setter
    public static class LoginRequest{
        private String id;
        private String password;
    }

    @Getter
    @Setter
    public static class Request {
        private String id;
    }

    @Builder
    @Getter
    public static class Response {
        private Info info;
        private BasicResponse basicResponse;
    }

    @Builder
    @Getter
    public static class TokenInfo {
        private String grantType;
        private String accessToken;
        private String refreshToken;
        private Long refreshTokenExpirationTime;
        private BasicResponse basicResponse;
    }
}
