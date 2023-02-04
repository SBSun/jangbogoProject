package backend.jangbogoProject.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private Long depth;
    private List<CategoryDTO> children;

    public static CategoryDTO of(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .depth(category.getDepth())
                .children(category.getChildren() == null ? null :
                        category.getChildren().stream().map(CategoryDTO::of).collect(Collectors.toList()))
                .build();
    }
}
