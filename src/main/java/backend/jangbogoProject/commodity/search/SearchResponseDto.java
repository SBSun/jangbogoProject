package backend.jangbogoProject.commodity.search;

import backend.jangbogoProject.commodity.CommodityInfoProjection;
import backend.jangbogoProject.commodity.market.MarketInfoProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class SearchResponseDto {
    @Getter
    @AllArgsConstructor
    public static class SearchDataList{
        private List<CommodityInfoProjection> commodityList;
        private List<MarketInfoProjection> marketList;
    }
}
