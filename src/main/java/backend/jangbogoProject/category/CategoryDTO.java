package backend.jangbogoProject.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private int categoryId;
    private String branch;
    private String name;
    private String parentCategoryName;
    private int level;
    private List<CategoryDTO> children;

    public CategoryDTO (Category entity) {

        this.categoryId = entity.getId();
        this.branch = entity.getBranch();
        this.name = entity.getName();
        this.level = entity.getLevel();
        if(entity.getParentCategory() == null) {
            this.parentCategoryName = "대분류";

        } else {

            this.parentCategoryName = entity.getParentCategory().getName();

        }

        // entity.getParentCategory이 null 일 경우
        this.children = entity.getSubCategory() == null ? null :
                entity.getSubCategory().stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    public Category toEntity () {
        return Category.builder()
                .branch(branch)
                .name(name)
                .level(level)
                .build();
    }
}
