package backend.jangbogoProject.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long category_id);
    Optional<Category> findByName(String name);

    Boolean existsByName(String name);
}
