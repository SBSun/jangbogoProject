package backend.jangbogoProject.entity.gu.controller;

import backend.jangbogoProject.entity.gu.dto.GuResponseDto;
import backend.jangbogoProject.entity.gu.service.GuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GuController {
    private final GuService guService;

    @GetMapping("/gus")
    public List<GuResponseDto.Info> findAllGuInfo(){
        return guService.findAllGuInfo();
    }
}
