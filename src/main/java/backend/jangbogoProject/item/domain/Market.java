package backend.jangbogoProject.item.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Market {
    private int guCode;
    private int marketSerialNum;
    private String marketName;

    public Market(int guCode, int marketSerialNum, String marketName) {
        this.guCode = guCode;
        this.marketSerialNum = marketSerialNum;
        this.marketName = marketName;
    }
}
