package backend.jangbogoProject.repository;

import backend.jangbogoProject.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT category_id FROM category WHERE branch = ?1 and name = ?2", nativeQuery = true)
    int findIdByBranchAndName(String branch, String name);
    Optional<Category> findByName (String name);
    Optional<Category> findById(int id);
    Optional<Category> findByBranchAndName (String branch, String name);
    //DB를 탐색해서 해당 카테고리가 있는지 없는지 여부
    Boolean existsByBranchAndName(String branch, String name);
}
