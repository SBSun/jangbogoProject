package backend.jangbogoProject.item;

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
    private Integer id;
    @Column(nullable = false)
    private String name;

    @Builder
    public Item(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
