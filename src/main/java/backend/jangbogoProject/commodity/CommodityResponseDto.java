package backend.jangbogoProject.commodity;

import backend.jangbogoProject.paging.PageResponseDTO;
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
