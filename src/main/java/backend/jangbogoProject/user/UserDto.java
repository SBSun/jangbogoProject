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
        private Long id;
        private String email;
        private String password;
        private String name;
        private String address;
    }

    @Getter
    @Setter
    public static class Request {
        private String email;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Info info;
        private int returnCode;
        private String returnMessage;
    }
}
