package backend.jangbogoProject.calldibs;

import com.google.gson.JsonObject;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CallDibsController {
    private final CallDibsService callDibsService;

    @PostMapping("/createCallDibs")
    public String createCallDibs(@RequestBody CallDibsDTO callDibsDTO){
        CallDibs saveCallDibs = callDibsService.save(callDibsDTO.toEntity());
        JsonObject data = new JsonObject();

        if(saveCallDibs == null){
            data.addProperty("res", "이미 찜 목록에 등록되어 있는 품목");
        }else{
            data.addProperty("res", "찜 등록 성공");
        }
        return data.toString();
    }
}
