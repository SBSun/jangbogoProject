package backend.jangbogoProject.entity.category.dto;

import backend.jangbogoProject.entity.category.Category;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponseDto {

    private Long id;
    private String name;
    private Long parentId;
    private int depth;
    private List<CategoryResponseDto> subCategories = new ArrayList<>();

    @Builder
    public CategoryResponseDto(Long id, String name, Long parentId, int depth) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.depth = depth;
    }
}
