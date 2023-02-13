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
            "WHERE c1.price > 0 " +
            "LIMIT ?2, ?3", nativeQuery = true)
    List<CommodityInfoProjection> getCommodities(int gu_id, int startIndex, int recordSize);

    @Query(value = "SELECT COUNT(*) " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "WHERE c1.price > 0", nativeQuery = true)
    int getCommodityCnt(int gu_id);

    @Query(value = "SELECT c1.commodity_id, m.name AS marketName, c2.name AS categoryName, " +
            "c1.unit, c1.price, c1.remarks, c1.p_date " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.name LIKE %?2% " +
            "WHERE c1.category_id = c2.category_id AND c1.price > 0 " +
            "LIMIT ?3, ?4", nativeQuery = true)
    List<CommodityInfoProjection> findByKeyword(int gu_id, String keyword, int startIndex, int recordSize);

    @Query(value = "SELECT COUNT(*) " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.name LIKE %?2% " +
            "WHERE c1.category_id = c2.category_id AND c1.price > 0", nativeQuery = true)
    int findByKeywordCnt(int gu_id, String keyword);

    @Query(value = "SELECT c1.commodity_id, m.name AS marketName, c2.name AS categoryName, " +
            "c1.unit, c1.price, c1.remarks, c1.p_date " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.parent_id = ?2 " +
            "WHERE c1.price > 0 AND c1.category_id = c2.category_id " +
            "LIMIT ?3, ?4", nativeQuery = true)
    List<CommodityInfoProjection> findByParentCategory(int gu_id, Long parent_id, int startIndex, int recordSize);

    @Query(value = "SELECT COUNT(*) " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.parent_id = ?2 " +
            "WHERE c1.price > 0 AND c1.category_id = c2.category_id", nativeQuery = true)
    int findByParentCategoryCnt(int gu_id, Long parent_id);

    @Query(value = "SELECT c1.commodity_id, m.name AS marketName, c2.name AS categoryName, " +
            "c1.unit, c1.price, c1.remarks, c1.p_date " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.category_id = ?2 " +
            "WHERE c1.price > 0 AND c1.category_id = c2.category_id " +
            "LIMIT ?3, ?4", nativeQuery = true)
    List<CommodityInfoProjection> findByChildCategory(int gu_id, Long category_id, int startIndex, int recordSize);

    @Query(value = "SELECT COUNT(*) " +
            "FROM commodity c1 " +
            "INNER JOIN market m ON m.gu_id = ?1 AND c1.market_id = m.market_id " +
            "INNER JOIN category c2 ON c2.category_id = ?2 " +
            "WHERE c1.price > 0 AND c1.category_id = c2.category_id", nativeQuery = true)
    int findByChildCategoryCnt(int gu_id, Long category_id);
}
