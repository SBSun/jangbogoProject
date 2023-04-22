package backend.jangbogoProject.entity.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MarketResponseDto {

    @Getter
    @AllArgsConstructor
    public static class Info{
        private Long marketId;
        private String name;
        private Long guId;

        public String toString(){
            return marketId + ", " + name + ", " + guId;
        }
    }
}
