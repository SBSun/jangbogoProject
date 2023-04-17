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

    @Query(value = "SELECT COUNT(*) " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "WHERE c1.price > 0", nativeQuery = true)
    int getCommodityCnt(int gu_id);

    @Query(value = "SELECT COUNT(*) " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.market_id = ?1 " +
            "INNER JOIN category c2 ON c1.category_id = c2.category_id " +
            "WHERE c1.price > 0 AND c1.market_id = m.market_id  ", nativeQuery = true)
    int findByMarketCnt(int market_id);

    @Query(value = "SELECT COUNT(*) " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.name LIKE %?2% " +
            "WHERE c1.category_id = c2.category_id AND c1.price > 0", nativeQuery = true)
    int findByKeywordCnt(int gu_id, String keyword);

    @Query(value = "SELECT COUNT(*) " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.parent_id = ?2 " +
            "WHERE c1.price > 0 AND c1.category_id = c2.category_id", nativeQuery = true)
    int findByParentCategoryCnt(int gu_id, int parent_id);

    @Query(value = "SELECT COUNT(*) " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.category_id = ?2 " +
            "WHERE c1.price > 0 AND c1.category_id = c2.category_id", nativeQuery = true)
    int findByChildCategoryCnt(int gu_id, int category_id);
}
