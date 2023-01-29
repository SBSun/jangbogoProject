package backend.jangbogoProject.commodity.gu;

import backend.jangbogoProject.dto.BasicResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class GuResponseDto {

    @Getter
    @AllArgsConstructor
    public static class GuInfoList{
        private List<GuInfoProjection> guInfoList;
        private BasicResponse basicResponse;
    }
}
