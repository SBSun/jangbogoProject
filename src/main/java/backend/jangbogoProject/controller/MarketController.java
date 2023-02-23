package backend.jangbogoProject.controller;

import backend.jangbogoProject.dto.MarketInfoProjection;
import backend.jangbogoProject.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketController {
    private final MarketService marketService;

    @GetMapping("/findMarketsInGu")
    private List<MarketInfoProjection> findMarketsInGu(int gu_id){
        List<MarketInfoProjection> list = marketService.findMarketsInGu(gu_id);

        return list;
    }
}
