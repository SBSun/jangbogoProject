package backend.jangbogoProject.entity.commodity.repository;

import backend.jangbogoProject.entity.commodity.dto.CommodityInfoProjection;
import backend.jangbogoProject.entity.commodity.dto.CommodityResponseDto;

import java.util.List;

public interface CommodityRepositoryCustom {

    List<CommodityResponseDto.Info> getCommodities(int gu_id, int startIndex, int recordSize);
}
