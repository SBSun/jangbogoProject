package backend.jangbogoProject.commodity.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Item {
    @Id
    @Column(name = "item_id")
    private int id;
    @Column(nullable = false)
    private String name;

    @Builder
    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
