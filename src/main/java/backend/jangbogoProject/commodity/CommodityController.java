package backend.jangbogoProject.commodity;

import backend.jangbogoProject.category.CategoryResponseDTO;
import backend.jangbogoProject.commodity.search.SearchRequestDTO;
import backend.jangbogoProject.dto.DataResponseDTO;
import backend.jangbogoProject.paging.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private CommodityResponseDto.CommodityInfoList findByCategory(int gu_id, SearchRequestDTO searchRequestDTO){
        return commodityService.findByCategory(gu_id, searchRequestDTO);
    }

    @GetMapping("/findByMarket")
    private CommodityResponseDto.CommodityInfoList findByMarket(SearchRequestDTO searchRequestDTO){
        return commodityService.findByMarket(searchRequestDTO);
    }

    @GetMapping("/findByKeyword")
    private CommodityResponseDto.CommodityInfoList findByKeyword(int gu_id, SearchRequestDTO searchRequestDTO){
        return commodityService.findByKeyword(gu_id, searchRequestDTO);
    }

    @PostMapping("/load_save")
    public String load_save(){
        return commodityService.load_save();
    }
}
