package backend.jangbogoProject.CRUDTest;

import backend.jangbogoProject.commodity.gu.Gu;
import backend.jangbogoProject.commodity.gu.GuRepository;
import backend.jangbogoProject.commodity.item.Item;
import backend.jangbogoProject.commodity.item.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CRUDTest {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private GuRepository guRepository;

    @Test
    public void saveItem(){
        Item item = Item.builder()
                .id(15)
                .name("사과")
                .build();

        itemRepository.save(item);
    }

    @Test
    public void saveGu(){
        Gu gu = Gu.builder()
                .id(1)
                .name("도봉구")
                .build();

        guRepository.save(gu);
    }
}