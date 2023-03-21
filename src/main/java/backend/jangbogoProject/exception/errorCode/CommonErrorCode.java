package backend.jangbogoProject.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode{

    // 400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "파라미터 값을 확인해주세요."),

    // 404 NOT_FOUND 잘못된 리소스 접근
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 값입니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 카테고리입니다."),
    PARENT_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 상위 카테고리입니다."),

    // 409 CONFLICT 중복된 리소스
    ALREADY_SAVED_CATEGORY(HttpStatus.CONFLICT, "이미 존재하는 카테고리입니다."),

    // 500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다. 서버 팀에 연락주세요!");

    private final HttpStatus httpStatus;
    private final String message;
}
