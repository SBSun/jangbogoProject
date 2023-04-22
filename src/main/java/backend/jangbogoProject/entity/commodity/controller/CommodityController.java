package backend.jangbogoProject.entity.commodity.controller;

import backend.jangbogoProject.dto.SearchRequestDTO;
import backend.jangbogoProject.entity.commodity.dto.CommodityResponseDto;
import backend.jangbogoProject.dto.DataResponseDTO;
import backend.jangbogoProject.entity.commodity.service.CommodityService;
import lombok.RequiredArgsConstructor;
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
    public DataResponseDTO<CommodityResponseDto.InfoList> findByMarket(SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.InfoList infoList = commodityService.findByMarket(searchRequestDTO);

        return DataResponseDTO.of(infoList);
    }

    @GetMapping("/findByKeyword")
    public DataResponseDTO<CommodityResponseDto.InfoList> findByKeyword(@RequestParam @NotNull Long guId, SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.InfoList infoList = commodityService.findByKeyword(guId, searchRequestDTO);

        return DataResponseDTO.of(infoList);
    }
}
