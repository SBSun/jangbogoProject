package backend.jangbogoProject.entity.commodity.dto;

import backend.jangbogoProject.dto.PageResponseDTO;
import lombok.*;

import java.util.List;

public class CommodityResponseDto {

    @Getter
    @AllArgsConstructor
    public static class Info{
        private int commodityId;
        private String marketName;
        private int categoryId;
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
        private List<Info> infoList;
        private PageResponseDTO pageResponseDTO;
    }
}
