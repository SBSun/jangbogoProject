package backend.jangbogoProject.calldibs;

import com.google.gson.JsonObject;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class CallDibsController {
    private final CallDibsService callDibsService;

    // http://localhost:8080/member/favorite
    @GetMapping("/mypage/calldibs")
    public ModelAndView calldibs() {

        ModelAndView mav = new ModelAndView("mypage/favorite");
        return mav;
    }

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

    @DeleteMapping("/deleteCallDibs")
    public String deleteCallDibs(@RequestBody CallDibsDTO callDibsDTO){
        CallDibs deleteCallDibs = callDibsService.delete(callDibsDTO.toEntity());
        JsonObject data = new JsonObject();

        if(deleteCallDibs == null){
            data.addProperty("res", "찜 목록에 존재하지 않는 품목입니다.");
        }else{
            data.addProperty("res", "찜 삭제 성공");
        }
        return data.toString();
    }
}
