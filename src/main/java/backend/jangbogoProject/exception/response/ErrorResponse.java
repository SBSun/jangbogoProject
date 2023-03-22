package backend.jangbogoProject.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {

    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY) // errors가 없다면 응답으로 내려가지 않도록
    private final List<ValidationError> errors;

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ValidationError { // @Valid를 사용했을 때 에러가 발생한 경우 어느 필드에서 에러가 발생했는지 응답을 위한 정적 클래스

        private final String field;
        private final String message;

        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ConstraintViolationError { // @Valid를 사용했을 때 에러가 발생한 경우 어느 필드에서 에러가 발생했는지 응답을 위한 정적 클래스

        private final String field;
        private final String message;

        public static ConstraintViolationError of(final FieldError fieldError) {
            return ConstraintViolationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }
}
