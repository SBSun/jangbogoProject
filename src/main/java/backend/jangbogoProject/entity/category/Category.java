package backend.jangbogoProject.entity.category;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @Column(name = "category_id")
    private Long id;

    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    private int depth;
}
