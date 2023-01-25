package backend.jangbogoProject.commodity.search;

import backend.jangbogoProject.commodity.CommodityInfoProjection;
import backend.jangbogoProject.commodity.CommodityService;
import backend.jangbogoProject.commodity.market.MarketInfoProjection;
import backend.jangbogoProject.commodity.market.MarketService;
import backend.jangbogoProject.dto.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final CommodityService commodityService;
    private final MarketService marketService;

    public SearchResponseDto.SearchDataList search(int gu_id, String find){
        List<CommodityInfoProjection> commodityInfoList = commodityService.getCommodityListFromGu(gu_id, find);
        List<MarketInfoProjection> marketInfoList = marketService.findMarketsByName(find);

        BasicResponse basicResponse;

        if(commodityInfoList.isEmpty() && marketInfoList.isEmpty()){
            basicResponse = BasicResponse.builder()
                    .message(find + "을 포함하는 품목 or 매장이 존재하지 않습니다.")
                    .state(404)
                    .build();
        }else
        {
            basicResponse = BasicResponse.builder()
                    .message("검색 단어를 포함하는 품목 or 매장 반환 성공")
                    .state(200)
                    .build();
        }

        SearchResponseDto.SearchDataList searchDataList = new SearchResponseDto.SearchDataList(commodityInfoList, marketInfoList, basicResponse);

        return searchDataList;
    }
}
