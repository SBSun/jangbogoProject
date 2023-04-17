package backend.jangbogoProject.entity.market.repository;

import backend.jangbogoProject.entity.market.dto.MarketInfoProjection;
import backend.jangbogoProject.entity.market.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MarketRepository extends JpaRepository<Market, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE market", nativeQuery = true)
    void truncateMarket();

    @Query(value = "SELECT name FROM item WHERE market_id = ?1", nativeQuery = true)
    String getMarketName(int id);

    @Query(value = "SELECT market_id AS marketId, name, gu_id AS guId FROM market WHERE gu_id = ?1", nativeQuery = true)
    List<MarketInfoProjection> findMarketsInGu(int gu_id);

    @Query(value = "SELECT market_id AS marketId, name, gu_id AS guId FROM market WHERE name LIKE %?1%", nativeQuery = true)
    List<MarketInfoProjection> findMarketsByName(String name);
}
