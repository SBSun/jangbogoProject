package backend.jangbogoProject.dto;

import backend.jangbogoProject.entity.commodity.dto.CommodityInfoProjection;
import backend.jangbogoProject.entity.market.dto.MarketInfoProjection;
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
