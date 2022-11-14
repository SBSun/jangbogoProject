package backend.jangbogoProject;

import backend.jangbogoProject.domain.Item;
import backend.jangbogoProject.domain.Market;
import backend.jangbogoProject.service.ItemService;
import backend.jangbogoProject.service.MarketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    void FindAllByCategoryInGu()
    {
        List<Item> items = itemService.findAllByCategoryInGu(740000, "해산물");

        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i).getItemName() + ", " + items.get(i).getItemPrice());
        }
    }

    @Test
    void 찜목록_품목반환()
    {
        List<Item> items = itemService.findAllByCallDibs("byung0216@naver.com");

        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i).getSerialNum() + ", " + items.get(i).getItemName());
        }
    }
}
