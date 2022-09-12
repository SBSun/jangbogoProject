package backend.jangbogoProject.item.repository;

import backend.jangbogoProject.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    public List<Item> findAllByItemSerialNum(int itemSerialNum);

    @Query(value = "SELECT DISTINCT i from Item i where i.itemName like %?1% or i.marketName like %?1%")
    public List<Item> findAllBySearch(String search);

    public List<Item> findAllByMarketSerialNum(int marketSerialNum);

    public List<Item> findAllByMarketGuCode(int marketGuCode);

    @Query(value = "SELECT DISTINCT * FROM Item i1, ( SELECT MIN(A_PRICE) A_PRICE, P_SEQ FROM Item i2 WHERE M_GU_CODE = ?1 GROUP BY A_SEQ) i2 WHERE i1.P_SEQ = i2.P_SEQ", nativeQuery = true)
    public List<Item> findAllByLowestPriceInGu(int marketGuCode);

    @Query(value = "select item.* from(select * from item where M_GU_CODE = ?1) item inner join item_info ON item.A_SEQ = item_info.A_SEQ inner join category on item_info.category_id = category.category_id \n" +
            "where category.branch = ?2", nativeQuery = true)
    public List<Item> findAllByCategoryInGu(int marketGuCode, String branch);

    @Query(value = "SELECT item.* FROM item, (SELECT * FROM calldibs WHERE email = ?1) calldibs WHERE item.P_SEQ = calldibs.P_SEQ", nativeQuery = true)
    public List<Item> findAllByCallDibs(String email);
}
