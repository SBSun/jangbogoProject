package backend.jangbogoProject.repository;

import backend.jangbogoProject.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long category_id);
    Optional<Category> findByName(String name);

    Boolean existsByName(String name);
}
