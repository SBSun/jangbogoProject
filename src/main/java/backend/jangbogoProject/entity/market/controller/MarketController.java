package backend.jangbogoProject.entity.market.controller;

import backend.jangbogoProject.entity.market.dto.MarketInfoProjection;
import backend.jangbogoProject.entity.market.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketController {
    private final MarketService marketService;

    @GetMapping("/findMarketsInGu")
    public List<MarketInfoProjection> findMarketsInGu(@RequestParam @NotNull int gu_id){
        List<MarketInfoProjection> list = marketService.findMarketsInGu(gu_id);

        return list;
    }
}
