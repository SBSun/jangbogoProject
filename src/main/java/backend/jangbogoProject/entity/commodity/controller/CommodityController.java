package backend.jangbogoProject.entity.commodity.controller;

import backend.jangbogoProject.dto.PageRequestDto;
import backend.jangbogoProject.dto.SearchRequestDTO;
import backend.jangbogoProject.entity.commodity.dto.CommodityResponseDto;
import backend.jangbogoProject.dto.DataResponseDTO;
import backend.jangbogoProject.entity.commodity.service.CommodityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/commodity")
public class CommodityController {
    private final CommodityService commodityService;

    @GetMapping("/getCommodities")
    public DataResponseDTO<CommodityResponseDto.InfoList> getCommodities(@RequestParam @NotNull Long guId, SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.InfoList infoList = commodityService.getCommodities(guId, searchRequestDTO);
        return DataResponseDTO.of(infoList);
    }

    @GetMapping("/getLowestPriceCommodities")
    public DataResponseDTO<CommodityResponseDto.InfoList> getLowestPriceCommodities(@RequestParam @NotNull Long guId){
        List<CommodityResponseDto.Info> infoList = commodityService.getLowestPriceCommodities(guId);

        return DataResponseDTO.of(new CommodityResponseDto.InfoList(infoList, null));
    }

    @GetMapping("/findByCategory")
    public DataResponseDTO<CommodityResponseDto.InfoList> findByCategory(@RequestParam @NotNull Long guId, SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.InfoList infoList = commodityService.findByCategory(guId, searchRequestDTO);

        return DataResponseDTO.of(infoList);
    }

    @GetMapping("/findByMarket")
    public Page<CommodityResponseDto.Info> findByMarket(@RequestParam @NotNull Long marketId, PageRequestDto pageRequestDto){

        Pageable pageable = pageRequestDto.of();
        Page<CommodityResponseDto.Info> infoList = commodityService.findByMarket(marketId, pageable);

        System.out.println(infoList.getContent());
        return infoList;
    }

    @GetMapping("/findByKeyword")
    public DataResponseDTO<CommodityResponseDto.InfoList> findByKeyword(@RequestParam @NotNull Long guId, SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.InfoList infoList = commodityService.findByKeyword(guId, searchRequestDTO);

        return DataResponseDTO.of(infoList);
    }
}
