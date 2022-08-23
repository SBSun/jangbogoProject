package backend.jangbogoProject.item.repository;

import backend.jangbogoProject.item.domain.Item;
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

    @Query(value = "SELECT DISTINCT i from Item i where i.marketGuCode = ?1" +
            "and i.itemPrice = (select max(i.itemPrice) from Item where i.itemSerialNum = Item.itemSerialNum)")
    public List<Item> findAllByLowestPriceInGu(int marketGuCode);
}
