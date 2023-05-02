package backend.jangbogoProject.entity.commodity.dto;
import lombok.*;

import java.util.List;

public class CommodityResponseDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info{
        private Long commodityId;
        private String marketName;
        private Long categoryId;
        private String categoryName;
        private String unit;
        private String price;
        private String remarks;
        private String date;

        public String toString(){
            return commodityId + ", " + marketName + ", " + categoryName + ", " + price + ", " + unit + ", " + remarks + ", " + date;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class InfoList{
        private List<CommodityResponseDto.Info> content;
    }
}
