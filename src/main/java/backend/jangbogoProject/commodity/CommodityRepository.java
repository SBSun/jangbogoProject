package backend.jangbogoProject.commodity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Integer> {

    @Query(value = "TRUNCATE TABLE commodity", nativeQuery = true)
    void truncateCommodity();

    @Query(value = "SELECT c1.commodity_id, m.name AS marketName, c2.name AS categoryName, " +
            "c1.unit, c1.price, c1.remarks, c1.p_date " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c1.category_id = c2.category_id " +
            "WHERE c1.price > 0 ", nativeQuery = true)
    List<CommodityInfoProjection> findCommoditiesInGu(int gu_id);

    @Query(value = "SELECT c1.commodity_id, m.name AS marketName, c2.name AS categoryName, " +
            "c1.unit, c1.price, c1.remarks, c1.p_date " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.name LIKE %?2% " +
            "WHERE c1.category_id = c2.category_id AND c1.price > 0", nativeQuery = true)
    List<CommodityInfoProjection> findSearchInGu(int gu_id, String find);

    @Query(value = "SELECT c1.commodity_id, m.name AS marketName, c2.name AS categoryName, " +
            "c1.unit, c1.price, c1.remarks, c1.p_date " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.parent_id = ?2 " +
            "WHERE c1.price > 0 AND c1.category_id = c2.category_id", nativeQuery = true)
    List<CommodityInfoProjection> findParentCategoryInGu(int gu_id, Long parent_id);

    @Query(value = "SELECT c1.commodity_id, m.name AS marketName, c2.name AS categoryName, " +
            "c1.unit, c1.price, c1.remarks, c1.p_date " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.category_id = ?2 " +
            "WHERE c1.price > 0 AND c1.category_id = c2.category_id", nativeQuery = true)
    List<CommodityInfoProjection> findChildCategoryInGu(int gu_id, Long category_id);
}
