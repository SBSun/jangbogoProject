package backend.jangbogoProject.serviceTest;

import backend.jangbogoProject.commodity.CommodityInfoProjection;
import backend.jangbogoProject.commodity.CommodityResponseDto;
import backend.jangbogoProject.commodity.CommodityService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
//@Run(SpringRunner.class)
public class CommodityServiceTest {

    @Autowired
    private CommodityService commodityService;

    @Test
    public void getCommodityListFromGu(){
        List<CommodityInfoProjection> list = commodityService.findCommodityListInGu(110000);

        System.out.println(list.toString());
    }
}
