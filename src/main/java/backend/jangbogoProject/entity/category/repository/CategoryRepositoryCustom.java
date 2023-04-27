package backend.jangbogoProject.entity.category.repository;

import backend.jangbogoProject.entity.category.Category;
import backend.jangbogoProject.entity.category.dto.CategoryResponseDto;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryCustom {

    Category findByName(String name);

    List<CategoryResponseDto> findByCategoryId(Long categoryId);

    List<CategoryResponseDto> findSubCategoriesByName(String name);

    Long findIdByName(String name);

    List<String> findNamesByDepth(int depth);
}
