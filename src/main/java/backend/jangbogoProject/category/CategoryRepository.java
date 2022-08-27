package backend.jangbogoProject.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName (String name);

    //DB를 탐색해서 해당 카테고리가 있는지 없는지 여부
    Boolean existsByName(String name);
}
