package backend.jangbogoProject.CRUDTest;

import backend.jangbogoProject.commodity.CommodityDto;
import backend.jangbogoProject.commodity.CommodityService;
import backend.jangbogoProject.commodity.gu.Gu;
import backend.jangbogoProject.commodity.gu.GuRepository;
import backend.jangbogoProject.commodity.gu.GuService;
import backend.jangbogoProject.commodity.item.Item;
import backend.jangbogoProject.commodity.item.ItemRepository;
import backend.jangbogoProject.commodity.item.ItemService;
import backend.jangbogoProject.commodity.market.Market;
import backend.jangbogoProject.commodity.market.MarketService;
import backend.jangbogoProject.user.UserDto;
import backend.jangbogoProject.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CRUDTest {

    @Autowired
    private MarketService marketService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private GuService guService;
    @Autowired
    private UserService userService;
/*
    @Test
    public void saveItem(){
        Item item = Item.builder()
                .id(15)
                .name("사과")
                .build();

        itemService.save(item);
    }

    @Test
    public void saveGu(){
        Gu gu = Gu.builder()
                .id(1)
                .name("도봉구")
                .build();

        guService.save(gu);
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

    @Test
    public void findCommodity(){
        CommodityDto.Response response = commodityService.findById(5);

        System.out.println(response);
    }

    @Test
    public void findUser(){
        UserDto.Response response = userService.findById("byung0216@naver.com");

        System.out.println(response);
    }

    @Test
    public void updateUserInfo(){
        UserDto.Info updateInfo = UserDto.Info.builder()
                .id("SBS")
                .password("SBS")
                .name("Sun")
                .address("도봉구 창4동")
                .build();

        userService.updateInfo(updateInfo);

        UserDto.Response response = userService.findById("SBS");

        if(response == null)
            new IllegalArgumentException("해당 회원은 존재하지 않습니다.");

        System.out.println(response);
    }*/
}