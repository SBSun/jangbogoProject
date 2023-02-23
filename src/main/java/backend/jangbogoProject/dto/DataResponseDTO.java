package backend.jangbogoProject.dto;

import backend.jangbogoProject.constant.StatusCode;
import lombok.Getter;

@Getter
public class DataResponseDTO<T> extends ResponseDTO{
    private final T data;

    private DataResponseDTO(T data) {
        super(StatusCode.OK.getStatus(), StatusCode.OK.getMessage());
        this.data = data;
    }

    public static <T> DataResponseDTO<T> of(T data) {
        return new DataResponseDTO<>(data);
    }
}
