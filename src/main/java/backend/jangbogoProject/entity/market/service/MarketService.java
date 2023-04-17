package backend.jangbogoProject.entity.market.service;

import backend.jangbogoProject.exception.errorCode.CommonErrorCode;
import backend.jangbogoProject.exception.exception.RestApiException;
import backend.jangbogoProject.entity.market.repository.MarketRepository;
import backend.jangbogoProject.entity.gu.Gu;
import backend.jangbogoProject.entity.market.dto.MarketInfoProjection;
import backend.jangbogoProject.entity.market.Market;
import backend.jangbogoProject.entity.gu.service.GuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MarketService {
    private final MarketRepository marketRepository;
    private final GuService guService;

    @Transactional
    public void save(Market _market, String guName){
        if(!guService.existsById(_market.getGu_id())){
            Gu gu = Gu.builder()
                    .id(_market.getGu_id())
                    .name(guName)
                    .build();
            guService.save(gu);
        }

        marketRepository.save(_market);
    }

    public Market findById(int id){
        Market market = marketRepository.findById(id).get();

        if(market == null)
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        return market;
    }

    public boolean existsById(int id){
        return marketRepository.existsById(id);
    }

    public String getMarketName(int id){
        String marketName = marketRepository.getMarketName(id);

        if(marketName.isEmpty())
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        return marketName;
    }

    public List<MarketInfoProjection> findMarketsInGu(int gu_id){
        List<MarketInfoProjection> marketInfoList = marketRepository.findMarketsInGu(gu_id);

        if(marketInfoList.isEmpty())
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        return marketInfoList;
    }

    public List<MarketInfoProjection> findMarketsByName(String name){
        List<MarketInfoProjection> marketInfoList = marketRepository.findMarketsByName(name);

        if(marketInfoList.isEmpty())
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        return marketInfoList;
    }

    public void truncateMarket(){
        marketRepository.truncateMarket();
    }
}
