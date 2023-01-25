package backend.jangbogoProject.commodity.market;

import backend.jangbogoProject.commodity.gu.Gu;
import backend.jangbogoProject.commodity.gu.GuRepository;
import backend.jangbogoProject.commodity.gu.GuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final MarketRepository marketRepository;
    private final GuService guService;

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
            new IllegalArgumentException("해당 객체는 존재하지 않습니다.");

        return market;
    }

    public boolean existsById(int id){
        return marketRepository.existsById(id);
    }

    public String getMarketName(int id){
        String marketName = marketRepository.getMarketName(id);

        if(marketName.isEmpty())
            new IllegalArgumentException("해당 객체는 존재하지 않습니다.");

        return marketName;
    }

    public List<MarketInfoProjection> findMarketsByName(String name){
        List<MarketInfoProjection> marketInfoList = marketRepository.findMarketsByName(name);

        return marketInfoList;
    }
}
