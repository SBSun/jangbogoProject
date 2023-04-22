package backend.jangbogoProject.entity.market.repository;

import backend.jangbogoProject.entity.market.dto.MarketResponseDto;

import java.util.List;

public interface MarketRepositoryCustom {

    MarketResponseDto.Info findById(Long id);

    String findNameById(Long id);

    List<MarketResponseDto.Info> findMarketsByGu(Long guId);

    List<MarketResponseDto.Info> findMarketsByName(String name);
}
