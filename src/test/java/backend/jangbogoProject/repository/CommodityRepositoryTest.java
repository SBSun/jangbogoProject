package backend.jangbogoProject.repository;

import backend.jangbogoProject.TestConfig;
import backend.jangbogoProject.entity.commodity.dto.CommodityResponseDto;
import backend.jangbogoProject.entity.commodity.repository.CommodityRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
public class CommodityRepositoryTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private CommodityRepository commodityRepository;

    @Test
    @DisplayName("구 내의 상품들 반환")
    public void getCommodities(){
        // given
        Long guId = 110000L;
        int startIndex = 0;
        int recordSize = 10;

        // when
        List<CommodityResponseDto.Info> commodityList = commodityRepository.getCommodities(guId, startIndex, recordSize);

        // then
        for (int i = 0; i < commodityList.size(); i++) {
            CommodityResponseDto.Info info = commodityList.get(i);
            System.out.println(info.toString());
        }
    }

    @Test
    @DisplayName("구 내의 검색한 상품들 반환")
    public void findByKeyword(){
        // given
        Long guId = 110000L;
        String keyword = "배";
        int startIndex = 0;
        int recordSize = 10;

        // when
        List<CommodityResponseDto.Info> commodityList = commodityRepository.findByKeyword(guId, keyword, startIndex, recordSize);

        // then
        for (int i = 0; i < commodityList.size(); i++) {
            CommodityResponseDto.Info info = commodityList.get(i);
            System.out.println(info.toString());
        }
    }

    @Test
    @DisplayName("특정 마켓 내 상품들 반환")
    public void findByMarket(){
        // given
        Long marketId = 1L;
        int startIndex = 0;
        int recordSize = 10;

        // when
        List<CommodityResponseDto.Info> commodityList = commodityRepository.findByMarket(marketId, startIndex, recordSize);

        // then
        for (int i = 0; i < commodityList.size(); i++) {
            CommodityResponseDto.Info info = commodityList.get(i);
            System.out.println(info.toString());
        }
    }

    @Test
    @DisplayName("구 내의 특정 카테고리 상품들 반환")
    public void findByChildCategory(){
        // given
        Long guId = 110000L;
        Long categoryId = 10L;   // 닭고기
        int startIndex = 0;
        int recordSize = 10;

        // when
        List<CommodityResponseDto.Info> commodityList = commodityRepository.findByChildCategory(guId, categoryId, startIndex, recordSize);

        // then
        for (int i = 0; i < commodityList.size(); i++) {
            CommodityResponseDto.Info info = commodityList.get(i);
            System.out.println(info.toString());
        }
    }

    @Test
    @DisplayName("구 내의 특정 상위 카테고리 상품들 반환")
    public void findByParentCategory(){
        // given
        Long guId = 110000L;
        Long parentId = 3L;    // 수산물
        int startIndex = 0;
        int recordSize = 10;

        // when
        List<CommodityResponseDto.Info> commodityList = commodityRepository.findByParentCategory(guId, parentId, startIndex, recordSize);

        // then
        for (int i = 0; i < commodityList.size(); i++) {
            CommodityResponseDto.Info info = commodityList.get(i);
            System.out.println(info.toString());
        }
    }

    @Test
    @DisplayName("구 내의 카테고리 별 최저가 상품들 반환")
    public void getLowestPriceCommodities(){
        // given
        Long guId = 110000L;

        // when
        List<CommodityResponseDto.Info> commodityList = commodityRepository.getLowestPriceCommodities(guId);

        // then
        for (int i = 0; i < commodityList.size(); i++) {
            CommodityResponseDto.Info info = commodityList.get(i);
            System.out.println(info.toString());
        }
    }
}
