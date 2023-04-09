package backend.jangbogoProject.repository;

import backend.jangbogoProject.TestConfig;
import backend.jangbogoProject.entity.commodity.dto.CommodityInfoProjection;
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
        int gu_id = 110000;
        int startIndex = 0;
        int recordSize = 10;

        // when
        List<CommodityInfoProjection> commodityList = commodityRepository.getCommodities(gu_id, startIndex, recordSize);

        // then
        for (int i = 0; i < commodityList.size(); i++) {
            CommodityInfoProjection info = commodityList.get(i);
            System.out.println(info.getMarketName() + ", " + info.getCategoryName() + ", " + info.getPrice());
        }
    }
}
