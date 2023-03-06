package backend.jangbogoProject.dto;

import backend.jangbogoProject.entity.User;
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
    public static class LoginInfo{
        private String name;
        private TokenInfo tokenInfo;
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
