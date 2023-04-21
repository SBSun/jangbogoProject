package backend.jangbogoProject.entity.commodity.repository;

import backend.jangbogoProject.entity.commodity.dto.CommodityResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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
    public List<CommodityResponseDto.Info> getCommodities(int gu_id, int startIndex, int recordSize) {
        return queryFactory
                .select(Projections.constructor(CommodityResponseDto.Info.class,
                    commodity.id,
                    market.name.as("marketName"),
                    commodity.category_id,
                    category.name.as("categoryName"),
                    commodity.A_UNIT,
                    commodity.A_PRICE,
                    commodity.ADD_COL,
                    commodity.P_DATE
                ))
                .from(commodity)
                .join(market)
                    .on(toEq(market.id, commodity.M_SEQ)
                            .and(toEq(market.gu_id, gu_id)))
                .join(category)
                    .on(toEq(category.id, commodity.category_id))
                .where(toNe(commodity.A_PRICE, "0"))
                .orderBy(category.name.asc())
                .offset(startIndex)
                .limit(recordSize)
                .fetch();
    }

    @Override
    public List<CommodityResponseDto.Info> findByKeyword(int gu_id, String keyword, int startIndex, int recordSize) {
        return queryFactory
                .select(Projections.constructor(CommodityResponseDto.Info.class,
                    commodity.id,
                    market.name.as("marketName"),
                    commodity.category_id,
                    category.name.as("categoryName"),
                    commodity.A_UNIT,
                    commodity.A_PRICE,
                    commodity.ADD_COL,
                    commodity.P_DATE
                ))
                .from(commodity)
                .join(market)
                    .on(toEq(market.id, commodity.M_SEQ)
                        .and(toEq(market.gu_id, gu_id)))
                .join(category)
                    .on(containsKeyword(category.name, keyword))
                .where(toNe(commodity.A_PRICE, "0")
                    .and(toEq(commodity.category_id, category.id)))
                .orderBy(category.name.asc())
                .offset(startIndex)
                .limit(recordSize)
                .fetch();
    }

    @Override
    public List<CommodityResponseDto.Info> findByMarket(int market_id, int startIndex, int recordSize) {
        return queryFactory
                .select(Projections.constructor(CommodityResponseDto.Info.class,
                    commodity.id,
                    market.name.as("marketName"),
                    commodity.category_id,
                    category.name.as("categoryName"),
                    commodity.A_UNIT,
                    commodity.A_PRICE,
                    commodity.ADD_COL,
                    commodity.P_DATE
                ))
                .from(commodity)
                .join(market)
                    .on(toEq(market.id, market_id))
                .join(category)
                    .on(toEq(category.id, commodity.category_id))
                .where(toNe(commodity.A_PRICE, "0")
                    .and(toEq(market.id, commodity.M_SEQ)))
                .orderBy(category.name.asc())
                .offset(startIndex)
                .limit(recordSize)
                .fetch();
    }

    @Override
    public List<CommodityResponseDto.Info> findByChildCategory(int gu_id, int category_id, int startIndex, int recordSize) {
        return queryFactory
                .select(Projections.constructor(CommodityResponseDto.Info.class,
                    commodity.id,
                    market.name.as("marketName"),
                    commodity.category_id,
                    category.name.as("categoryName"),
                    commodity.A_UNIT,
                    commodity.A_PRICE,
                    commodity.ADD_COL,
                    commodity.P_DATE
                ))
                .from(commodity)
                .join(market)
                    .on(toEq(market.gu_id, gu_id))
                .join(category)
                    .on(toEq(category.id, category_id))
                .where(toNe(commodity.A_PRICE, "0")
                    .and(toEq(category.id, commodity.category_id))
                    .and(toEq(market.id, commodity.M_SEQ)))
                .orderBy(category.name.asc())
                .offset(startIndex)
                .limit(recordSize)
                .fetch();
    }

    @Override
    public List<CommodityResponseDto.Info> findByParentCategory(int gu_id, int parent_id, int startIndex, int recordSize) {
        return queryFactory
                .select(Projections.constructor(CommodityResponseDto.Info.class,
                    commodity.id,
                    market.name.as("marketName"),
                    commodity.category_id,
                    category.name.as("categoryName"),
                    commodity.A_UNIT,
                    commodity.A_PRICE,
                    commodity.ADD_COL,
                    commodity.P_DATE
                ))
                .from(commodity)
                .join(market)
                    .on(toEq(market.gu_id, gu_id))
                .join(category)
                    .on(toEq(category.id, commodity.category_id))
                .where(toNe(commodity.A_PRICE, "0")
                    .and(toEq(category.parent.id, parent_id))
                    .and(toEq(market.id, commodity.M_SEQ)))
                .orderBy(category.name.asc())
                .offset(startIndex)
                .limit(recordSize)
                .fetch();
    }

    @Override
    public List<CommodityResponseDto.Info> getLowestPriceCommodities(int gu_id) {

        List<Integer> marketIds = queryFactory
                .select(market.id)
                .from(market)
                .where(toEq(market.gu_id, gu_id))
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
                        market.name.as("marketName"),
                        commodity.category_id,
                        category.name.as("categoryName"),
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
