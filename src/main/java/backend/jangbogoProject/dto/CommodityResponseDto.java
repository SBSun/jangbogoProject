package backend.jangbogoProject.dto;

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
