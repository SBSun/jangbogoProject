package backend.jangbogoProject.entity.commodity.controller;

import backend.jangbogoProject.dto.PageRequestDto;
import backend.jangbogoProject.entity.commodity.dto.CommodityResponseDto;
import backend.jangbogoProject.dto.DataResponseDTO;
import backend.jangbogoProject.entity.commodity.service.CommodityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/commodity")
public class CommodityController {
    private final CommodityService commodityService;

    @GetMapping("/getCommodities")
    public Page<CommodityResponseDto.Info> getCommodities(@RequestParam @NotNull Long guId
            , PageRequestDto pageRequestDto){
        Pageable pageable = pageRequestDto.of();
        Page<CommodityResponseDto.Info> infoList = commodityService.getCommodities(guId, pageable);

        return infoList;
    }

    @GetMapping("/getLowestPriceCommodities")
    public List<CommodityResponseDto.Info> getLowestPriceCommodities(@RequestParam @NotNull Long guId){
        List<CommodityResponseDto.Info> infoList = commodityService.getLowestPriceCommodities(guId);

        return infoList;
    }

    @GetMapping("/findByCategory")
    public Page<CommodityResponseDto.Info> findByCategory(@RequestParam @NotNull Long guId
            , @RequestParam @NotBlank String categoryName, PageRequestDto pageRequestDto){
        Pageable pageable = pageRequestDto.of();
        Page<CommodityResponseDto.Info> infoList = commodityService.findByCategory(guId, categoryName, pageable);

        return infoList;
    }

    @GetMapping("/findByMarket")
    public Page<CommodityResponseDto.Info> findByMarket(@RequestParam @NotNull Long marketId
            , PageRequestDto pageRequestDto){
        Pageable pageable = pageRequestDto.of();
        Page<CommodityResponseDto.Info> infoList = commodityService.findByMarket(marketId, pageable);

        System.out.println(infoList.getContent());
        return infoList;
    }

    @GetMapping("/findByKeyword")
    public Page<CommodityResponseDto.Info> findByKeyword(@RequestParam @NotNull Long guId
            , @RequestParam @NotBlank String keyword, PageRequestDto pageRequestDto){
        Pageable pageable = pageRequestDto.of();
        Page<CommodityResponseDto.Info> infoList = commodityService.findByKeyword(guId, keyword, pageable);

        return infoList;
    }
}
