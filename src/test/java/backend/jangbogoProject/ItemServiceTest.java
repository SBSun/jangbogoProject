package backend.jangbogoProject;

import backend.jangbogoProject.config.SpringConfig;
import backend.jangbogoProject.item.domain.Item;
import backend.jangbogoProject.item.service.ItemService;
import backend.jangbogoProject.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ItemServiceTest {
    @Autowired
    private  ItemService itemService;

    @Test
    void FindAllA_SEQ()
    {
        List<Item> items = itemService.findAllBySearch("이");

        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i).getMarketName() + ", " + items.get(i).getItemName());
        }
    }

    @Test
    void FindAllByLowestPriceInGu()
    {
        List<Item> items = itemService.findAllByLowestPriceInGu(320000);

        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i).getMarketName() + ", " + items.get(i).getItemName());
        }
    }
}
