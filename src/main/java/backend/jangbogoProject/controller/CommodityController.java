package backend.jangbogoProject.controller;

import backend.jangbogoProject.aop.ExecutionTimeChecker;
import backend.jangbogoProject.dto.SearchRequestDTO;
import backend.jangbogoProject.dto.CommodityInfoProjection;
import backend.jangbogoProject.dto.CommodityResponseDto;
import backend.jangbogoProject.dto.DataResponseDTO;
import backend.jangbogoProject.service.CommodityService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/commodity")
public class CommodityController {
    private final CommodityService commodityService;

    @GetMapping("/getCommodities")
    public DataResponseDTO<CommodityResponseDto.CommodityInfoList> getCommodities(@RequestParam @NotBlank int gu_id, SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.CommodityInfoList infoList = commodityService.getCommodities(gu_id, searchRequestDTO);
        return DataResponseDTO.of(infoList);
    }

    @GetMapping("/getLowestPriceCommodities")
    public DataResponseDTO<CommodityResponseDto.CommodityInfoList> getLowestPriceCommodities(@RequestParam @Min(1) int gu_id){
        System.out.println(gu_id);
        List<CommodityInfoProjection> infoList = commodityService.getLowestPriceCommodities(gu_id);

        return DataResponseDTO.of(new CommodityResponseDto.CommodityInfoList(infoList, null));
    }

    @GetMapping("/findByCategory")
    public DataResponseDTO<CommodityResponseDto.CommodityInfoList> findByCategory(@RequestParam @Min(1) int gu_id, SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.CommodityInfoList infoList = commodityService.findByCategory(gu_id, searchRequestDTO);
        return DataResponseDTO.of(infoList);
    }

    @GetMapping("/findByMarket")
    public DataResponseDTO<CommodityResponseDto.CommodityInfoList> findByMarket(SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.CommodityInfoList infoList = commodityService.findByMarket(searchRequestDTO);
        return DataResponseDTO.of(infoList);
    }

    @GetMapping("/findByKeyword")
    public DataResponseDTO<CommodityResponseDto.CommodityInfoList> findByKeyword(@RequestParam @Min(1) int gu_id, SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.CommodityInfoList infoList = commodityService.findByKeyword(gu_id, searchRequestDTO);
        return DataResponseDTO.of(infoList);
    }

    @PostMapping("/load_save")
    public String load_save(){
        return commodityService.load_save();
    }
}
