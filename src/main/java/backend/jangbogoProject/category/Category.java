package backend.jangbogoProject.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String branch;
    private String code;
    private String name;
    private Integer level;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name ="parent_id")
    private Category parentCategory;

    @OneToMany (mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Category> subCategory = new ArrayList<>();

    @Builder
    public Category(Long id, String branch, String code, String name, Integer level, List<Category> subCategory) {
        this.id = id;
        this.branch = branch;
        this.code = code;
        this.name = name;
        this.level = level;
        this.subCategory = subCategory;
    }
}
