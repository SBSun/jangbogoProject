package backend.jangbogoProject.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName (String name);

    Boolean existsByName(String name);
}
