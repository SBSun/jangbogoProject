package backend.jangbogoProject.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{

    INACTIVE_USER(HttpStatus.FORBIDDEN, "You don't have permission to access");

    private final HttpStatus httpStatus;
    private final String message;
}
