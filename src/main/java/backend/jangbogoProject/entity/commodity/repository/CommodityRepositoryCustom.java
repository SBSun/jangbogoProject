package backend.jangbogoProject.entity.commodity.repository;

import backend.jangbogoProject.entity.commodity.dto.CommodityResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommodityRepositoryCustom {

    Page<CommodityResponseDto.Info> getCommodities(Long guId, Pageable pageable);

    Page<CommodityResponseDto.Info> findByKeyword(Long guId, String keyword, Pageable pageable);

    Page<CommodityResponseDto.Info> findByMarket(Long marketId, Pageable pageable);

    Page<CommodityResponseDto.Info> findByChildCategory(Long guId, Long categoryId, Pageable pageable);

    Page<CommodityResponseDto.Info> findByParentCategory(Long guId, Long parentId, Pageable pageable);

    List<CommodityResponseDto.Info> getLowestPriceCommodities(Long guId);

    void truncate();

    void foreignKeyChecksOn();
}
