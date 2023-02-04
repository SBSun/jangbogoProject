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
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String parentName;
    private Long depth;
    private List<CategoryResponseDTO> children;

    public static CategoryResponseDTO of(Category category){
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .parentName(category.getParent().getName())
                .depth(category.getDepth())
                .children(category.getChildren() == null ? null :
                        category.getChildren().stream().map(CategoryResponseDTO::of).collect(Collectors.toList()))
                .build();
    }
}
