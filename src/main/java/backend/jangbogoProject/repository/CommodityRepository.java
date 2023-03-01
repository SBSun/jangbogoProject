package backend.jangbogoProject.repository;

import backend.jangbogoProject.dto.CommodityInfoProjection;
import backend.jangbogoProject.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE commodity", nativeQuery = true)
    void truncateCommodity();

    @Query(value = "ALTER TABLE commodity AUTO_INCREMENT = 1", nativeQuery = true)
    void resetIncrement();

    @Query(value = "SELECT c1.commodity_id, m.name AS marketName,c1.category_id ,c2.name AS categoryName, " +
            "c1.unit, c1.price, c1.remarks, c1.p_date " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c1.category_id = c2.category_id " +
            "WHERE c1.price > 0 " +
            "ORDER BY categoryName " +
            "LIMIT ?2, ?3", nativeQuery = true)
    List<CommodityInfoProjection> getCommodities(int gu_id, int startIndex, int recordSize);

    @Query(value = "SELECT COUNT(*) " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "WHERE c1.price > 0", nativeQuery = true)
    int getCommodityCnt(int gu_id);

    @Query(value =
            "SELECT c1.commodity_id, c1.market_name AS marketName, c1.category_id, cate.name AS categoryName, c1.price, c1.unit, c1.remarks, c1.p_date\n" +
            "FROM (\n" +
            "\tSELECT c.commodity_id, m.name AS market_name, c.category_id, c.price, c.unit, c.remarks, c.p_date\n" +
            "    FROM commodity c\n" +
            "    INNER JOIN market m ON m.gu_id = ?1\n" +
            "    WHERE c.market_id = m.market_id\n" +
            ") c1\n" +
            "INNER JOIN category cate ON cate.category_id = c1.category_id\n" +
            "INNER JOIN(\n" +
            "\tSELECT c2.commodity_id, c2.market_id, c2.category_id, MIN(price) AS price, c2.unit, c2.remarks, c2.p_date\n" +
            "    FROM commodity c2\n" +
            "\tINNER JOIN market m ON m.gu_id = ?1\n" +
            "\tWHERE c2.market_id = m.market_id AND c2.price > 0\n" +
            "    GROUP BY c2.category_id\n" +
            ") c3 ON c1.price = c3.price AND c1.category_id = c3.category_id " +
            "", nativeQuery = true)

    List<CommodityInfoProjection> getLowestPriceCommodities(int gu_id);

    @Query(value = "SELECT c1.commodity_id, m.name AS marketName, c1.category_id, c2.name AS categoryName, " +
            "c1.unit, c1.price, c1.remarks, c1.p_date " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.market_id = ?1 " +
            "INNER JOIN category c2 ON c1.category_id = c2.category_id " +
            "WHERE c1.price > 0  AND c1.market_id = m.market_id " +
            "ORDER BY categoryName " +
            "LIMIT ?2, ?3", nativeQuery = true)
    List<CommodityInfoProjection> findByMarket(int market_id, int startIndex, int recordSize);

    @Query(value = "SELECT COUNT(*) " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.market_id = ?1 " +
            "INNER JOIN category c2 ON c1.category_id = c2.category_id " +
            "WHERE c1.price > 0 AND c1.market_id = m.market_id  ", nativeQuery = true)
    int findByMarketCnt(int market_id);


    @Query(value = "SELECT c1.commodity_id, m.name AS marketName, c1.category_id, c2.name AS categoryName, " +
            "c1.unit, c1.price, c1.remarks, c1.p_date " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.name LIKE %?2% " +
            "WHERE c1.category_id = c2.category_id AND c1.price > 0 " +
            "ORDER BY categoryName " +
            "LIMIT ?3, ?4", nativeQuery = true)
    List<CommodityInfoProjection> findByKeyword(int gu_id, String keyword, int startIndex, int recordSize);

    @Query(value = "SELECT COUNT(*) " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.name LIKE %?2% " +
            "WHERE c1.category_id = c2.category_id AND c1.price > 0", nativeQuery = true)
    int findByKeywordCnt(int gu_id, String keyword);

    @Query(value = "SELECT c1.commodity_id, m.name AS marketName, c1.category_id, c2.name AS categoryName, " +
            "c1.unit, c1.price, c1.remarks, c1.p_date " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.parent_id = ?2 " +
            "WHERE c1.price > 0 AND c1.category_id = c2.category_id " +
            "ORDER BY categoryName " +
            "LIMIT ?3, ?4", nativeQuery = true)
    List<CommodityInfoProjection> findByParentCategory(int gu_id, Long parent_id, int startIndex, int recordSize);

    @Query(value = "SELECT COUNT(*) " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.parent_id = ?2 " +
            "WHERE c1.price > 0 AND c1.category_id = c2.category_id", nativeQuery = true)
    int findByParentCategoryCnt(int gu_id, Long parent_id);

    @Query(value = "SELECT c1.commodity_id, m.name AS marketName, c1.category_id, c2.name AS categoryName, " +
            "c1.unit, c1.price, c1.remarks, c1.p_date " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.category_id = ?2 " +
            "WHERE c1.price > 0 AND c1.category_id = c2.category_id " +
            "ORDER BY categoryName " +
            "LIMIT ?3, ?4", nativeQuery = true)
    List<CommodityInfoProjection> findByChildCategory(int gu_id, Long category_id, int startIndex, int recordSize);

    @Query(value = "SELECT COUNT(*) " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.category_id = ?2 " +
            "WHERE c1.price > 0 AND c1.category_id = c2.category_id", nativeQuery = true)
    int findByChildCategoryCnt(int gu_id, Long category_id);
}
