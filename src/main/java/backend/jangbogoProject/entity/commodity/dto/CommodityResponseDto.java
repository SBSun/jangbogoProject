package backend.jangbogoProject.entity.commodity.dto;

import backend.jangbogoProject.dto.PageResponseDTO;
import lombok.*;

import java.util.List;

public class CommodityResponseDto {
    @Getter
    @AllArgsConstructor
    public static class CommodityInfoList{
        private List<CommodityInfoProjection> infoList;
        private PageResponseDTO pageResponseDTO;
    }
}
