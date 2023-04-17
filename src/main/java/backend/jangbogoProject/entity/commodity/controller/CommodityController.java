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
    public DataResponseDTO<CommodityResponseDto.InfoList> getCommodities(@RequestParam @NotNull int gu_id, SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.InfoList infoList = commodityService.getCommodities(gu_id, searchRequestDTO);
        return DataResponseDTO.of(infoList);
    }

    @GetMapping("/getLowestPriceCommodities")
    public DataResponseDTO<CommodityResponseDto.InfoList> getLowestPriceCommodities(@RequestParam @NotNull int gu_id){
        System.out.println(gu_id);
        List<CommodityResponseDto.Info> infoList = commodityService.getLowestPriceCommodities(gu_id);

        return DataResponseDTO.of(new CommodityResponseDto.InfoList(infoList, null));
    }

    @GetMapping("/findByCategory")
    public DataResponseDTO<CommodityResponseDto.InfoList> findByCategory(@RequestParam @NotNull int gu_id, SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.InfoList infoList = commodityService.findByCategory(gu_id, searchRequestDTO);
        return DataResponseDTO.of(infoList);
    }

    @GetMapping("/findByMarket")
    public DataResponseDTO<CommodityResponseDto.InfoList> findByMarket(SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.InfoList infoList = commodityService.findByMarket(searchRequestDTO);
        return DataResponseDTO.of(infoList);
    }

    @GetMapping("/findByKeyword")
    public DataResponseDTO<CommodityResponseDto.InfoList> findByKeyword(@RequestParam @NotNull int gu_id, SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.InfoList infoList = commodityService.findByKeyword(gu_id, searchRequestDTO);
        return DataResponseDTO.of(infoList);
    }
}
