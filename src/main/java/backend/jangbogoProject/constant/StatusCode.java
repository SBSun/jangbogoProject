package backend.jangbogoProject.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StatusCode {
    OK(HttpStatus.OK.value(), "OK"),

    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "Bad request"),

    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Requested resource is not found");

    private final Integer status;
    private final String message;
}
