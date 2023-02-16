package backend.jangbogoProject.user;

import backend.jangbogoProject.dto.BasicResponse;
import lombok.*;

import java.util.List;

public class UserResponseDto {

    @Getter
    @Builder
    public static class Info{
        private Long user_id;
        private String email;
        private String password;
        private String name;
        private String address;

        public static Info of(User user){
            return Info.builder()
                    .user_id(user.getId())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .name(user.getName())
                    .address(user.getAddress())
                    .build();
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class TokenInfo {
        private String grantType;
        private String accessToken;
        private String refreshToken;
        private Long refreshTokenExpirationTime;
        private BasicResponse basicResponse;

        public TokenInfo(BasicResponse basicResponse){
            this.basicResponse = basicResponse;
        }
    }
}
