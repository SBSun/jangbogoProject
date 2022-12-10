package backend.jangbogoProject.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserDto {
    @Getter
    @AllArgsConstructor
    @Builder
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
    public static class Request {
        private String id;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Info info;
        private int returnCode;
        private String returnMessage;
    }
}
