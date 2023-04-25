package backend.jangbogoProject.entity.category.repository;

import backend.jangbogoProject.entity.category.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryCustom {

    Category findByName(String name);

    List<String> findNamesByDepth(int depth);
}
