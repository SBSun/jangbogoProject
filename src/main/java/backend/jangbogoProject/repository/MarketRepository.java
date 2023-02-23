package backend.jangbogoProject.repository;

import backend.jangbogoProject.dto.MarketInfoProjection;
import backend.jangbogoProject.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarketRepository extends JpaRepository<Market, Integer> {
    @Query(value = "SELECT name FROM item WHERE market_id = ?1", nativeQuery = true)
    String getMarketName(int id);

    @Query(value = "SELECT market_id AS marketId, name, gu_id AS guId FROM market WHERE gu_id = ?1", nativeQuery = true)
    List<MarketInfoProjection> findMarketsInGu(int gu_id);

    @Query(value = "SELECT market_id AS marketId, name, gu_id AS guId FROM market WHERE name LIKE %?1%", nativeQuery = true)
    List<MarketInfoProjection> findMarketsByName(String name);
}
