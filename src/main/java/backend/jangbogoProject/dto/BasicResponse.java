package backend.jangbogoProject.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class BasicResponse {

    private int state;
    private String message;
}
