package backend.jangbogoProject.entity.commodity.repository;

import backend.jangbogoProject.entity.commodity.dto.CommodityInfoProjection;

import java.util.List;

public interface CommodityRepositoryCustom {

    List<CommodityInfoProjection> getCommodities(int gu_id, int startIndex, int recordSize);
}
