package backend.jangbogoProject.commodity;

import backend.jangbogoProject.dto.BasicResponse;
import lombok.*;

import javax.persistence.Column;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CommodityResponseDto {
    @Getter
    @AllArgsConstructor
    public static class CommodityInfo{
        private CommodityInfoProjection info;
        private BasicResponse basicResponse;
    }

    @Getter
    @AllArgsConstructor
    public static class CommodityInfoList{
        private List<CommodityInfoProjection> infoList;
        private BasicResponse basicResponse;
    }
}
