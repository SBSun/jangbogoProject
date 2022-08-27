package backend.jangbogoProject.item.repository;

import backend.jangbogoProject.item.domain.Item;
import backend.jangbogoProject.item.domain.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    public List<Item> findAllByItemSerialNum(int itemSerialNum);

    @Query(value = "SELECT DISTINCT i from Item i where i.itemName like %?1% or i.marketName like %?1%")
    public List<Item> findAllBySearch(String search);

    public List<Item> findAllByMarketSerialNum(int marketSerialNum);

    public List<Item> findAllByMarketGuCode(int marketGuCode);

    @Query(value = "SELECT DISTINCT * FROM Item i1, ( SELECT MIN(A_PRICE) A_PRICE, P_SEQ FROM Item i2 WHERE M_GU_CODE = ?1 GROUP BY A_SEQ) i2 WHERE i1.P_SEQ = i2.P_SEQ", nativeQuery = true)
    public List<Item> findAllByLowestPriceInGu(int marketGuCode);

    @Query(value = "SELECT * FROM Item WHERE M_GU_CODE = ?1 GROUP BY M_SEQ ORDER BY M_NAME", nativeQuery = true)
    public List<Item> findAllByMarketsInGu(int marketGuCode);
}
