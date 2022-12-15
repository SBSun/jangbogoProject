package backend.jangbogoProject.commodity.market;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MarketRepository extends JpaRepository<Market, Integer> {
    @Query(value = "SELECT name FROM item WHERE market_id = ?1", nativeQuery = true)
    public String getMarketName(int id);
}
