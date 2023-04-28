package backend.jangbogoProject.repository;

import backend.jangbogoProject.TestConfig;
import backend.jangbogoProject.entity.market.dto.MarketResponseDto;
import backend.jangbogoProject.entity.market.repository.MarketRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
public class MarketRepositoryTest {

    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private MarketRepository marketRepository;

    @Test
    @DisplayName("Id로 Market 정보 반환")
    public void findByMarketId(){
        // given
        Long marketId = 11l;

        // when
        MarketResponseDto.Info info = marketRepository.findByMarketId(marketId);

        // then
        System.out.println(info.toString());
    }

    @Test
    @DisplayName("Id로 Market name 반환")
    public void findNameById(){
        // given
        Long marketId = 11l;

        // when
        String name = marketRepository.findNameById(marketId);

        // then
        System.out.println(name);
    }

    @Test
    @DisplayName("구 내의 모든 Market 반환")
    public void findMarketsByGu(){
        // given
        Long guId = 170000l;

        // when
        List<MarketResponseDto.Info> infoList = marketRepository.findMarketsByGu(guId);

        // then
        for (int i = 0; i < infoList.size(); i++) {
            MarketResponseDto.Info info = infoList.get(i);
            System.out.println(info.toString());
        }
    }

    @Test
    @DisplayName("이름이 포함된 모든 Market 반환")
    public void findMarketsByName(){
        // given
        String name = "이";

        // when
        List<MarketResponseDto.Info> infoList = marketRepository.findMarketsByName(name);

        // then
        for (int i = 0; i < infoList.size(); i++) {
            MarketResponseDto.Info info = infoList.get(i);
            System.out.println(info.toString());
        }
    }
}
