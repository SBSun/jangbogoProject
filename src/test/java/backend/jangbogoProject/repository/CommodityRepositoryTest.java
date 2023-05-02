package backend.jangbogoProject.repository;

import backend.jangbogoProject.TestConfig;
import backend.jangbogoProject.dto.PageRequestDto;
import backend.jangbogoProject.entity.commodity.Commodity;
import backend.jangbogoProject.entity.commodity.dto.CommodityResponseDto;
import backend.jangbogoProject.entity.commodity.repository.CommodityBulkRepository;
import backend.jangbogoProject.entity.commodity.repository.CommodityRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.ArrayList;
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
        PageRequestDto pageRequest = new PageRequestDto(1, 10);
        Pageable pageable = pageRequest.of();

        // when
        Page<CommodityResponseDto.Info> infoList = commodityRepository.getCommodities(guId, pageable);

        // then
        for (int i = 0; i < infoList.getNumberOfElements(); i++) {
            CommodityResponseDto.Info info = infoList.getContent().get(i);
            System.out.println(info.toString());
        }
    }

    @Test
    @DisplayName("구 내의 검색한 상품들 반환")
    public void findByKeyword(){
        // given
        Long guId = 110000L;
        String keyword = "배";
        PageRequestDto pageRequest = new PageRequestDto(1, 10);
        Pageable pageable = pageRequest.of();

        // when
        Page<CommodityResponseDto.Info> infoList = commodityRepository.findByKeyword(guId, keyword, pageable);

        // then
        for (int i = 0; i < infoList.getNumberOfElements(); i++) {
            CommodityResponseDto.Info info = infoList.getContent().get(i);
            System.out.println(info.toString());
        }
    }

    @Test
    @DisplayName("특정 마켓 내 상품들 반환")
    public void findByMarket(){
        // given
        Long marketId = 1L;
        PageRequestDto pageRequest = new PageRequestDto(1, 10);
        Pageable pageable = pageRequest.of();

        // when
        Page<CommodityResponseDto.Info> infoList = commodityRepository.findByMarket(marketId, pageable);

        // then
        for (int i = 0; i < infoList.getNumberOfElements(); i++) {
            CommodityResponseDto.Info info = infoList.getContent().get(i);
            System.out.println(info.toString());
        }
    }

    @Test
    @DisplayName("구 내의 특정 카테고리 상품들 반환")
    public void findByChildCategory(){
        // given
        Long guId = 110000L;
        Long categoryId = 10L;   // 닭고기
        PageRequestDto pageRequest = new PageRequestDto(1, 10);
        Pageable pageable = pageRequest.of();

        // when
        Page<CommodityResponseDto.Info> infoList = commodityRepository.findByChildCategory(guId, categoryId, pageable);

        // then
        for (int i = 0; i < infoList.getNumberOfElements(); i++) {
            CommodityResponseDto.Info info = infoList.getContent().get(i);
            System.out.println(info.toString());
        }
    }

    @Test
    @DisplayName("구 내의 특정 상위 카테고리 상품들 반환")
    public void findByParentCategory(){
        // given
        Long guId = 110000L;
        Long parentId = 3L;    // 수산물
        PageRequestDto pageRequest = new PageRequestDto(1, 10);
        Pageable pageable = pageRequest.of();

        // when
        Page<CommodityResponseDto.Info> infoList = commodityRepository.findByParentCategory(guId, parentId, pageable);

        // then
        for (int i = 0; i < infoList.getNumberOfElements(); i++) {
            CommodityResponseDto.Info info = infoList.getContent().get(i);
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
