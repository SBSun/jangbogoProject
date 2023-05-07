package backend.jangbogoProject.entity.market.controller;

import backend.jangbogoProject.entity.market.dto.MarketResponseDto;
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
public class MarketController {
    private final MarketService marketService;

    @GetMapping("/markets")
    public List<MarketResponseDto.Info> findMarketsInGu(@RequestParam @NotNull Long guId){
        List<MarketResponseDto.Info> list = marketService.findMarketsInGu(guId);

        return list;
    }
}
