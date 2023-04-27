package backend.jangbogoProject.entity.market.repository;

import backend.jangbogoProject.entity.market.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MarketRepository extends JpaRepository<Market, Long>, MarketRepositoryCustom {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE market", nativeQuery = true)
    void truncateMarket();
}
