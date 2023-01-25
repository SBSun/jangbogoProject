package backend.jangbogoProject.commodity.market;

import backend.jangbogoProject.commodity.CommodityInfoProjection;
import backend.jangbogoProject.dto.BasicResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class MarketResponseDto {
    @Getter
    @AllArgsConstructor
    public static class MarketInfoList{
        private List<MarketInfoProjection> marketList;
        private BasicResponse basicResponse;
    }
}
