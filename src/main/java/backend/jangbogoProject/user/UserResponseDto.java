package backend.jangbogoProject.user;

import backend.jangbogoProject.dto.BasicResponse;
import lombok.*;

import java.util.List;

public class UserResponseDto {

    @Getter
    @Builder
    public static class Info{
        private String user_id;
        private String password;
        private String name;
        private String address;

        public static Info of(User user){
            return Info.builder()
                    .user_id(user.getId())
                    .password(user.getPassword())
                    .name(user.getName())
                    .address(user.getAddress())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    public static class InfoList{
        private List<UserInfo> infoList;
        private BasicResponse basicResponse;
    }

    @Builder
    @Getter
    public static class Response {
        private UserInfo info;
        private BasicResponse basicResponse;
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
