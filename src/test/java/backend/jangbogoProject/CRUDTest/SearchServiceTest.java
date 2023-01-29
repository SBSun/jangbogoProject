package backend.jangbogoProject.CRUDTest;

import backend.jangbogoProject.commodity.market.MarketInfoProjection;
import backend.jangbogoProject.commodity.market.MarketService;
import backend.jangbogoProject.commodity.search.SearchResponseDto;
import backend.jangbogoProject.commodity.search.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
public class SearchServiceTest {

    @Autowired
    private SearchService searchService;
    @Autowired
    private MarketService marketService;

    @Test
    public void search(){
        SearchResponseDto.SearchDataList searchDataList = searchService.search(110000, "고등어");

        System.out.println(searchDataList.toString());
    }

    @Test
    public void marketSearch(){
        List<MarketInfoProjection> marketInfoList = marketService.findMarketsByName("고");

        System.out.println("marketInfoList Count : " + marketInfoList.size());
    }
}
