package backend.jangbogoProject.commodity.gu;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gu")
public class GuController {
    private final GuService guService;

    @GetMapping("/findAllGuInfo")
    private GuResponseDto.GuInfoList findAllGuInfo(){
        return guService.findAllGuInfo();
    }
}
