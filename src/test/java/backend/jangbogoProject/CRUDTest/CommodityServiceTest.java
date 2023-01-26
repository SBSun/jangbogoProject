package backend.jangbogoProject.CRUDTest;

import backend.jangbogoProject.commodity.CommodityInfoProjection;
import backend.jangbogoProject.commodity.CommodityResponseDto;
import backend.jangbogoProject.commodity.CommodityService;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommodityServiceTest {

    @Autowired
    private CommodityService commodityService;

    @Test
    public void getCommodityListFromGu(){
        List<CommodityInfoProjection> list = commodityService.findCommodityListInGu(110000);

        System.out.println(list);
    }
}
