package backend.jangbogoProject.CRUDTest;

import backend.jangbogoProject.commodity.CommodityService;
import backend.jangbogoProject.commodity.gu.Gu;
import backend.jangbogoProject.commodity.gu.GuRepository;
import backend.jangbogoProject.commodity.item.Item;
import backend.jangbogoProject.commodity.item.ItemRepository;
import backend.jangbogoProject.commodity.market.Market;
import backend.jangbogoProject.commodity.market.MarketService;
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

    @Autowired
    private MarketService marketService;
    @Autowired
    private CommodityService commodityService;

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

    @Test
    public void saveMarket(){
        Market market = Market.builder()
                .id(1)
                .name("창동 하나로마트")
                .gu_id(10)
                .build();

        marketService.save(market, "도봉구");
    }

    @Test
    public void load_save(){
        commodityService.load_save();
    }
}