package backend.jangbogoProject.entity.user.dto;

import backend.jangbogoProject.entity.user.User;
import lombok.*;

public class UserResponseDto {

    @Getter
    @Builder
    public static class Info{
        private Long user_id;
        private String email;
        private String password;
        private String name;
        private String loginType;

        public static Info of(User user){
            return Info.builder()
                    .user_id(user.getId())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .name(user.getName())
                    .loginType(user.getLoginType())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    public static class LoginSuccessInfo {
        private String name;
        private String loginType;
        private TokenInfo tokenInfo;
    }

    @Getter
    @AllArgsConstructor
    public static class LoginUserInfo {
        private String email;
        private String name;
        private String loginType;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class TokenInfo {
        private String grantType;
        private String accessToken;
        private String refreshToken;
        private Long refreshTokenExpirationTime;
    }
}
