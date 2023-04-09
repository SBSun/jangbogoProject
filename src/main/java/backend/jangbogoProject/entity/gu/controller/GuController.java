package backend.jangbogoProject.entity.gu.controller;

import backend.jangbogoProject.entity.gu.dto.GuInfoProjection;
import backend.jangbogoProject.entity.gu.service.GuService;
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
    public List<GuInfoProjection> findAllGuInfo(){
        return guService.findAllGuInfo();
    }
}
