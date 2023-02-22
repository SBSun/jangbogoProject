package backend.jangbogoProject.dto;

import backend.jangbogoProject.constant.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.aspectj.apache.bcel.classfile.Code;

@Getter
@RequiredArgsConstructor
public class ResponseDTO {
    private final Integer status;
    private final String message;

    public static ResponseDTO of(StatusCode statusCode) {
        return new ResponseDTO(statusCode.getStatus(), statusCode.getMessage());
    }
}
