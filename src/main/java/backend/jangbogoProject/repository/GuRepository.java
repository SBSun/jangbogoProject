package backend.jangbogoProject.repository;

import backend.jangbogoProject.dto.GuInfoProjection;
import backend.jangbogoProject.entity.Gu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuRepository extends JpaRepository<Gu, Integer> {
    @Query(value = "SELECT name FROM gu WHERE gu_id = ?1", nativeQuery = true)
    public String getGuName(int id);

    @Query(value = "SELECT * FROM gu", nativeQuery = true)
    public List<GuInfoProjection> findAllGuInfo();
}
