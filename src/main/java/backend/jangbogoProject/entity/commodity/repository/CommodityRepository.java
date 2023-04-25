package backend.jangbogoProject.entity.commodity.repository;

import backend.jangbogoProject.entity.commodity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommodityRepository extends JpaRepository<Commodity, Integer>, CommodityRepositoryCustom  {

    @Query(value = "ALTER TABLE commodity AUTO_INCREMENT = 1", nativeQuery = true)
    void resetIncrement();
}
