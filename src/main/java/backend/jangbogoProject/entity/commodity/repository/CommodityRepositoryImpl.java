package backend.jangbogoProject.entity.commodity.repository;

import backend.jangbogoProject.entity.commodity.dto.CommodityResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

import static backend.jangbogoProject.entity.commodity.QCommodity.commodity;
import static backend.jangbogoProject.entity.market.QMarket.market;
import static backend.jangbogoProject.entity.category.QCategory.category;
import static backend.jangbogoProject.utils.RepositorySupport.*;

@RequiredArgsConstructor
@Repository
public class CommodityRepositoryImpl implements CommodityRepositoryCustom{

    @Autowired
    private final EntityManager em;
    @Autowired
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommodityResponseDto.Info> getCommodities(Long guId, int startIndex, int recordSize) {
        return queryFactory
                .select(Projections.constructor(CommodityResponseDto.Info.class,
                    commodity.id,
                    market.name,
                    commodity.category_id,
                    category.name,
                    commodity.A_UNIT,
                    commodity.A_PRICE,
                    commodity.ADD_COL,
                    commodity.P_DATE
                ))
                .from(commodity)
                .join(market)
                    .on(toEq(market.id, commodity.M_SEQ)
                            .and(toEq(market.gu_id, guId)))
                .join(category)
                    .on(toEq(category.id, commodity.category_id))
                .where(toNe(commodity.A_PRICE, "0"))
                .orderBy(category.name.asc())
                .offset(startIndex)
                .limit(recordSize)
                .fetch();
    }

    @Override
    public List<CommodityResponseDto.Info> findByKeyword(Long guId, String keyword, int startIndex, int recordSize) {
        return queryFactory
                .select(Projections.constructor(CommodityResponseDto.Info.class,
                    commodity.id,
                    market.name,
                    commodity.category_id,
                    category.name,
                    commodity.A_UNIT,
                    commodity.A_PRICE,
                    commodity.ADD_COL,
                    commodity.P_DATE
                ))
                .from(commodity)
                .join(market)
                    .on(toEq(market.id, commodity.M_SEQ)
                        , toEq(market.gu_id, guId))
                .join(category)
                    .on(containsKeyword(category.name, keyword))
                .where(toNe(commodity.A_PRICE, "0")
                    , toEq(commodity.category_id, category.id))
                .orderBy(category.name.asc())
                .offset(startIndex)
                .limit(recordSize)
                .fetch();
    }

    @Override
    public Page<CommodityResponseDto.Info> findByMarket(Long marketId, Pageable pageable) {
        List<CommodityResponseDto.Info> infoList = queryFactory
                .select(Projections.constructor(CommodityResponseDto.Info.class,
                    commodity.id,
                    market.name,
                    commodity.category_id,
                    category.name,
                    commodity.A_UNIT,
                    commodity.A_PRICE,
                    commodity.ADD_COL,
                    commodity.P_DATE
                ))
                .from(commodity)
                .join(market)
                    .on(toEq(market.id, marketId))
                .join(category)
                    .on(toEq(category.id, commodity.category_id))
                .where(toNe(commodity.A_PRICE, "0")
                    , toEq(market.id, commodity.M_SEQ))
                .orderBy(category.name.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(commodity.count())
                .from(commodity)
                .join(market)
                    .on(toEq(market.id, marketId))
                .join(category)
                    .on(toEq(category.id, commodity.category_id))
                .where(toNe(commodity.A_PRICE, "0")
                        , toEq(market.id, commodity.M_SEQ))
                .fetchOne();

        return new PageImpl<>(infoList, pageable, count);
    }

    @Override
    public List<CommodityResponseDto.Info> findByChildCategory(Long guId, Long categoryId, int startIndex, int recordSize) {
        return queryFactory
                .select(Projections.constructor(CommodityResponseDto.Info.class,
                    commodity.id,
                    market.name,
                    commodity.category_id,
                    category.name,
                    commodity.A_UNIT,
                    commodity.A_PRICE,
                    commodity.ADD_COL,
                    commodity.P_DATE
                ))
                .from(commodity)
                .join(market)
                    .on(toEq(market.gu_id, guId))
                .join(category)
                    .on(toEq(category.id, categoryId))
                .where(toNe(commodity.A_PRICE, "0")
                    , toEq(category.id, commodity.category_id)
                    , toEq(market.id, commodity.M_SEQ))
                .orderBy(category.name.asc())
                .offset(startIndex)
                .limit(recordSize)
                .fetch();
    }

    @Override
    public List<CommodityResponseDto.Info> findByParentCategory(Long guId, Long parentId, int startIndex, int recordSize) {
        return queryFactory
                .select(Projections.constructor(CommodityResponseDto.Info.class,
                    commodity.id,
                    market.name,
                    commodity.category_id,
                    category.name,
                    commodity.A_UNIT,
                    commodity.A_PRICE,
                    commodity.ADD_COL,
                    commodity.P_DATE
                ))
                .from(commodity)
                .join(market)
                    .on(toEq(market.gu_id, guId))
                .join(category)
                    .on(toEq(category.id, commodity.category_id))
                .where(toNe(commodity.A_PRICE, "0")
                    .and(toEq(category.parent.id, parentId))
                    .and(toEq(market.id, commodity.M_SEQ)))
                .orderBy(category.name.asc())
                .offset(startIndex)
                .limit(recordSize)
                .fetch();
    }

    @Override
    public List<CommodityResponseDto.Info> getLowestPriceCommodities(Long guId) {

        List<Long> marketIds = queryFactory
                .select(market.id)
                .from(market)
                .where(toEq(market.gu_id, guId))
                .fetch();

        System.out.println(marketIds.size());

        List<Tuple> commodityIds = queryFactory
                .select(commodity.category_id, commodity.A_PRICE.min())
                .from(commodity)
                .where(commodity.M_SEQ.in(marketIds)
                        .and(toNe(commodity.A_PRICE, "0")))
                .groupBy(commodity.category_id)
                .fetch();

        BooleanBuilder builder = new BooleanBuilder();
        for (int i = 0; i < commodityIds.size(); i++) {
            builder.or(commodity.category_id.eq(commodityIds.get(i).get(commodity.category_id)).and(commodity.A_PRICE.eq(commodityIds.get(i).get(1, String.class))));
        }

        return queryFactory
                .select(Projections.constructor(CommodityResponseDto.Info.class,
                        commodity.id,
                        market.name,
                        commodity.category_id,
                        category.name,
                        commodity.A_UNIT,
                        commodity.A_PRICE,
                        commodity.ADD_COL,
                        commodity.P_DATE
                ))
                .from(commodity)
                .join(market)
                        .on(market.id.in(marketIds))
                .join(category)
                        .on(toEq(category.id, commodity.category_id))
                .where(toEq(market.id, commodity.M_SEQ)
                        .and(builder))
                .orderBy(category.name.asc())
                .fetch();
    }

    @Override
    public void truncate() {
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE commodity").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE market").executeUpdate();
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }
}
