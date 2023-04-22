package backend.jangbogoProject.entity.market.service;

import backend.jangbogoProject.entity.market.dto.MarketResponseDto;
import backend.jangbogoProject.exception.errorCode.CommonErrorCode;
import backend.jangbogoProject.exception.exception.RestApiException;
import backend.jangbogoProject.entity.market.repository.MarketRepository;
import backend.jangbogoProject.entity.gu.Gu;
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

        /*
        if(!guService.existsById(_market.getGu_id())){
            Gu gu = Gu.builder()
                    .id(_market.getGu_id())
                    .name(guName)
                    .build();

            guService.save(gu);
        }*/

        marketRepository.save(_market);
    }

    public MarketResponseDto.Info findById(Long id){
        MarketResponseDto.Info info = marketRepository.findById(id);

        if(info == null)
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        return info;
    }

    public boolean existsById(Long id){
        return marketRepository.existsById(id.intValue());
    }

    public String getMarketName(Long id){
        String marketName = marketRepository.findNameById(id);

        if(marketName.isEmpty())
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        return marketName;
    }

    public List<MarketResponseDto.Info> findMarketsInGu(Long gu_id){
        List<MarketResponseDto.Info> marketInfoList = marketRepository.findMarketsByGu(gu_id);

        if(marketInfoList.isEmpty())
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        return marketInfoList;
    }

    public List<MarketResponseDto.Info> findMarketsByName(String name){
        List<MarketResponseDto.Info> infoList = marketRepository.findMarketsByName(name);

        if(infoList.isEmpty())
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        return infoList;
    }

    public void truncateMarket(){
        marketRepository.truncateMarket();
    }
}
