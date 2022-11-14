package backend.jangbogoProject.service;

import backend.jangbogoProject.domain.Market;
import backend.jangbogoProject.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class MarketService {

    private final MarketRepository marketRepository;

    public List<Market> findAllByMarketsInGu(int marketGuCode)
    {
        List<Market> marketList = marketRepository.findAllByMarketsInGu(marketGuCode);

        return marketList;
    }
}
