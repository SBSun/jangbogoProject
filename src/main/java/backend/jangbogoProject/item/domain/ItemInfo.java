package backend.jangbogoProject.item.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "item_info")
@NoArgsConstructor
public class ItemInfo {
    @Column(name="A_SEQ")
    private int itemSerialNum; // 품목 번호
    @Column(name="A_NAME")
    private String itemName; // 품목 이름
    @Column (name ="category_id")
    private int categoryId;

    public ItemInfo(int itemSerialNum, String itemName, int categoryId) {
        this.itemSerialNum = itemSerialNum;
        this.itemName = itemName;
        this.categoryId = categoryId;
    }
}
