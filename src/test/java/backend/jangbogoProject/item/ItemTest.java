package backend.jangbogoProject.item;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemTest {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void saveItem(){
        Item item = Item.builder()
                .id(15)
                .name("사과")
                .build();

        itemRepository.save(item);
    }
}