package backend.jangbogoProject.item.service;

import backend.jangbogoProject.item.domain.Item;
import backend.jangbogoProject.item.domain.Market;
import backend.jangbogoProject.item.repository.ItemRepository;
import backend.jangbogoProject.item.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
