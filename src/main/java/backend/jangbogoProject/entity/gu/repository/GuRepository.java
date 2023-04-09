package backend.jangbogoProject.entity.gu.repository;

import backend.jangbogoProject.entity.gu.dto.GuInfoProjection;
import backend.jangbogoProject.entity.gu.Gu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuRepository extends JpaRepository<Gu, Integer> {
    @Query(value = "SELECT name FROM gu WHERE gu_id = ?1", nativeQuery = true)
    public String getGuName(int id);

    @Query(value = "SELECT * FROM gu", nativeQuery = true)
    public List<GuInfoProjection> findAllGuInfo();
}
