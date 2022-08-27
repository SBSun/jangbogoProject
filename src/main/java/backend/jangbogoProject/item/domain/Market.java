package backend.jangbogoProject.item.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Market {
    @Id
    @Column(name="M_SEQ")
    private int marketSerialNum;
    @Column(name="M_NAME")
    private String marketName;
    @Column(name="M_GU_CODE")
    private int guCode;

    public Market(int marketSerialNum, String marketName, int guCode) {
        this.marketSerialNum = marketSerialNum;
        this.marketName = marketName;
        this.guCode = guCode;
    }
}
