package backend.jangbogoProject.entity.commodity.repository;

import backend.jangbogoProject.entity.commodity.dto.CommodityResponseDto;

import java.util.List;

public interface CommodityRepositoryCustom {

    List<CommodityResponseDto.Info> getCommodities(int gu_id, int startIndex, int recordSize);

    List<CommodityResponseDto.Info> findByKeyword(int gu_id, String keyword, int startIndex, int recordSize);

    List<CommodityResponseDto.Info> findByMarket(int market_id, int startIndex, int recordSize);

    List<CommodityResponseDto.Info> findByChildCategory(int gu_id, int category_id, int startIndex, int recordSize);

    List<CommodityResponseDto.Info> findByParentCategory(int gu_id, int parent_id, int startIndex, int recordSize);

    List<CommodityResponseDto.Info> getLowestPriceCommodities(int gu_id);

    void truncate();
}
