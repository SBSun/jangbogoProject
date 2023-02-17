package backend.jangbogoProject.commodity.gu;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gu")
public class GuController {
    private final GuService guService;

    @GetMapping("/findAllGuInfo")
    private List<GuInfoProjection> findAllGuInfo(){
        return guService.findAllGuInfo();
    }
}
