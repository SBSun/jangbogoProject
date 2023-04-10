package backend.jangbogoProject.entity.commodity.repository;

import backend.jangbogoProject.entity.commodity.Commodity;
import backend.jangbogoProject.entity.commodity.dto.CommodityInfoProjection;
import backend.jangbogoProject.entity.commodity.dto.CommodityResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static backend.jangbogoProject.entity.commodity.QCommodity.commodity;
import static backend.jangbogoProject.entity.market.QMarket.market;
import static backend.jangbogoProject.entity.category.QCategory.category;

@RequiredArgsConstructor
@Repository
public class CommodityRepositoryImpl implements CommodityRepositoryCustom{

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
                    .on(market.id.eq(commodity.M_SEQ).and(market.gu_id.eq(gu_id)))
                .join(category)
                    .on(category.id.eq(commodity.category_id))
                .where(commodity.A_PRICE.ne("0"))
                .orderBy(category.name.asc())
                .offset(startIndex)
                .limit(recordSize)
                .fetch();
    }
}
