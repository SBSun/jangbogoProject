package backend.jangbogoProject.commodity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Integer> {
    @Query(value = "SELECT c.commodity_id AS commodityId, i.item_id AS itemId, m.name AS marketName, i.name AS itemName, c.unit AS unit, c.price AS price, c.p_date AS date " +
            "FROM commodity c " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c.market_id = m.market_id AND c.price > 0, item i " +
            "WHERE c.item_id = i.item_id", nativeQuery = true)
    List<CommodityInfoProjection> getCommodityListFromGu(int gu_id);
}
