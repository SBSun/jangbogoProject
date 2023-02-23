package backend.jangbogoProject.controller;

import backend.jangbogoProject.dto.SearchRequestDTO;
import backend.jangbogoProject.dto.CommodityInfoProjection;
import backend.jangbogoProject.dto.CommodityResponseDto;
import backend.jangbogoProject.dto.DataResponseDTO;
import backend.jangbogoProject.service.CommodityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commodity")
public class CommodityController {
    private final CommodityService commodityService;

    @GetMapping("/getCommodities")
    private DataResponseDTO<CommodityResponseDto.CommodityInfoList> getCommodities(int gu_id, SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.CommodityInfoList infoList = commodityService.getCommodities(gu_id, searchRequestDTO);
        return DataResponseDTO.of(infoList);
    }

    @GetMapping("/getLowestPriceCommodities")
    private DataResponseDTO<CommodityResponseDto.CommodityInfoList> getLowestPriceCommodities(int gu_id){
        List<CommodityInfoProjection> infoList = commodityService.getLowestPriceCommodities(gu_id);

        return DataResponseDTO.of(new CommodityResponseDto.CommodityInfoList(infoList, null));
    }

    @GetMapping("/findByCategory")
    private DataResponseDTO<CommodityResponseDto.CommodityInfoList> findByCategory(int gu_id, SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.CommodityInfoList infoList = commodityService.findByCategory(gu_id, searchRequestDTO);
        return DataResponseDTO.of(infoList);
    }

    @GetMapping("/findByMarket")
    private DataResponseDTO<CommodityResponseDto.CommodityInfoList> findByMarket(SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.CommodityInfoList infoList = commodityService.findByMarket(searchRequestDTO);
        return DataResponseDTO.of(infoList);
    }

    @GetMapping("/findByKeyword")
    private DataResponseDTO<CommodityResponseDto.CommodityInfoList> findByKeyword(int gu_id, SearchRequestDTO searchRequestDTO){
        CommodityResponseDto.CommodityInfoList infoList = commodityService.findByKeyword(gu_id, searchRequestDTO);
        return DataResponseDTO.of(infoList);
    }

    @PostMapping("/load_save")
    public String load_save(){
        return commodityService.load_save();
    }
}
