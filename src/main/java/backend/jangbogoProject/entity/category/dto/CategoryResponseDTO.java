package backend.jangbogoProject.entity.category.dto;

import backend.jangbogoProject.entity.category.Category;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String parentName;
    private int depth;
    private List<CategoryResponseDTO> children;

    public static CategoryResponseDTO of(Category category){
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .parentName(category.getParent() == null ? null : category.getParent().getName())
                .depth(category.getDepth())
                .children(category.getChildren() == null ? null :
                        category.getChildren().stream().map(CategoryResponseDTO::of).collect(Collectors.toList()))
                .build();
    }
}
