package backend.jangbogoProject.entity.market.repository;

import backend.jangbogoProject.entity.market.dto.MarketResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static backend.jangbogoProject.entity.market.QMarket.market;
import static backend.jangbogoProject.utils.RepositorySupport.*;

@RequiredArgsConstructor
@Repository
public class MarketRepositoryImpl implements MarketRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public MarketResponseDto.Info findById(Long id) {
        return queryFactory
                .select(Projections.constructor(MarketResponseDto.Info.class,
                        market.id,
                        market.name,
                        market.gu_id
                ))
                .from(market)
                .where(toEq(market.id, id))
                .fetchOne();
    }

    @Override
    public String findNameById(Long marketId) {
        return queryFactory
                .select(market.name)
                .from(market)
                .where(toEq(market.id, marketId))
                .fetchOne();
    }

    @Override
    public List<MarketResponseDto.Info> findMarketsByGu(Long guId) {
        return queryFactory
                .select(Projections.constructor(MarketResponseDto.Info.class,
                    market.id,
                    market.name,
                    market.gu_id
                ))
                .from(market)
                .where(toEq(market.gu_id, guId))
                .fetch();
    }

    @Override
    public List<MarketResponseDto.Info> findMarketsByName(String name) {
        return queryFactory
                .select(Projections.constructor(MarketResponseDto.Info.class,
                        market.id,
                        market.name,
                        market.gu_id
                ))
                .from(market)
                .where(containsKeyword(market.name, name))
                .fetch();
    }
}
