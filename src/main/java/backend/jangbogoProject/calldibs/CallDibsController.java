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

    @PostMapping("/callDibs")
    public String createCallDibs(@RequestBody CallDibsDTO callDibsDTO){
        callDibsService.save(callDibsDTO.toEntity());

        JsonObject data = new JsonObject();
        data.addProperty("res", "찜 등록");

        return data.toString();
    }
}
