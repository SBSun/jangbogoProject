package backend.jangbogoProject;

import backend.jangbogoProject.item.domain.Item;
import backend.jangbogoProject.item.domain.Market;
import backend.jangbogoProject.item.service.ItemService;
import backend.jangbogoProject.item.service.MarketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.error.Mark;

import java.util.List;

@SpringBootTest
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    private MarketService marketService;

    @Test
    void FindAllA_SEQ()
    {
        List<Item> items = itemService.findAllBySearch("이");

        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i).getMarketName() + ", " + items.get(i).getItemName());
        }
    }

    @Test
    void FindAllByMarketGuCode()
    {
        List<Market> marketList = marketService.findAllByMarketsInGu(320000);

        for (int i = 0; i < marketList.size(); i++) {
            System.out.println(marketList.get(i).getGuCode() + ", " + marketList.get(i).getMarketName());
        }
    }
}
