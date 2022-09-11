package backend.jangbogoProject.calldibs;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CallDibsDTO {
    private int id;
    private String email;
    private int serialNum;

    public CallDibs toEntity(){
        CallDibs build = CallDibs.builder()
                .id(id)
                .email(email)
                .serialNum(serialNum)
                .build();
        return build;
    }

    @Builder
    public CallDibsDTO(int id, String email, int serialNum) {
        this.id = id;
        this.email = email;
        this.serialNum = serialNum;
    }
}
