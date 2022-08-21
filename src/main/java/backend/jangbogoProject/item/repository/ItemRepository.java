package backend.jangbogoProject.item.repository;

import backend.jangbogoProject.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    public List<Item> findAllByItemSerialNum(int itemSerialNum);

    public List<Item> findAllByItemName(String itemName);

    public List<Item> findAllByMarketSerialNum(int marketSerialNum);

    public List<Item> findAllByMarketGuCode(int marketGuCode);
}
