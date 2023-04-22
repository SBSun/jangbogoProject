package backend.jangbogoProject.dto;

import backend.jangbogoProject.entity.commodity.dto.CommodityResponseDto;
import backend.jangbogoProject.entity.market.dto.MarketResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class SearchResponseDto {
    @Getter
    @AllArgsConstructor
    public static class SearchDataList{
        private List<CommodityResponseDto.Info> commodityList;
        private List<MarketResponseDto.Info> marketList;
    }
}
