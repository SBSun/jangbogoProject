package backend.jangbogoProject.entity.commodity.repository;

import backend.jangbogoProject.entity.commodity.dto.CommodityResponseDto;

import java.util.List;

public interface CommodityRepositoryCustom {

    List<CommodityResponseDto.Info> getCommodities(Long guId, int startIndex, int recordSize);

    List<CommodityResponseDto.Info> findByKeyword(Long guId, String keyword, int startIndex, int recordSize);

    List<CommodityResponseDto.Info> findByMarket(Long marketId, int startIndex, int recordSize);

    List<CommodityResponseDto.Info> findByChildCategory(Long guId, Long categoryId, int startIndex, int recordSize);

    List<CommodityResponseDto.Info> findByParentCategory(Long guId, Long parentId, int startIndex, int recordSize);

    List<CommodityResponseDto.Info> getLowestPriceCommodities(Long guId);

    void truncate();
}
