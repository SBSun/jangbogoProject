package backend.jangbogoProject.entity.category.repository;

import backend.jangbogoProject.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
    Optional<Category> findById(Long categoryId);

    Boolean existsByName(String name);
}
