package backend.jangbogoProject.item.domain;

import lombok.*;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    @Column(name="P_SEQ")
    private int serialNum; // 일련번호
    @Column(name="M_SEQ")
    private int marketSerialNum; // 마트 번호
    @Column(name="M_NAME")
    private String marketName; // 마트 이름
    @Column(name="A_SEQ")
    private int itemSerialNum; // 품목 번호
    @Column(name="A_NAME")
    private String itemName; // 품목 이름
    @Column(name="A_UNIT")
    private String itemUnit; // 품목 단위
    @Column(name="A_PRICE")
    private int itemPrice; // 품목 가격
    @Column(name="M_TYPE_CODE")
    private BigInteger marketTypeCode; // 시장 유형 코드
    @Column(name="M_TYPE_NAME")
    private String marketTypeName; // 시장 유형 이름
    @Column(name="M_GU_CODE")
    private int marketGuCode; // 자치구 코드
    @Column(name="M_GU_NAME")
    private String marketGuName; // 자치구 이름

    @Builder
    public Item(int serialNum, int marketSerialNum, String marketName, int itemSerialNum, String itemName, String itemUnit, int itemPrice, BigInteger marketTypeCode, String marketTypeName, int marketGuCode, String marketGuName) {
        this.serialNum = serialNum;
        this.marketSerialNum = marketSerialNum;
        this.marketName = marketName;
        this.itemSerialNum = itemSerialNum;
        this.itemName = itemName;
        this.itemUnit = itemUnit;
        this.itemPrice = itemPrice;
        this.marketTypeCode = marketTypeCode;
        this.marketTypeName = marketTypeName;
        this.marketGuCode = marketGuCode;
        this.marketGuName = marketGuName;
    }
}
