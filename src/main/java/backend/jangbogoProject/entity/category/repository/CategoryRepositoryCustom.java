package backend.jangbogoProject.entity.category.repository;

import backend.jangbogoProject.entity.category.Category;
import backend.jangbogoProject.entity.category.dto.CategoryResponseDto;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryCustom {

    CategoryResponseDto findByCategoryId(Long categoryId);

    CategoryResponseDto findByName(String name);

    List<String> findNamesByDepth(int depth);
}
