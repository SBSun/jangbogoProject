package backend.jangbogoProject.item.service;

import backend.jangbogoProject.item.domain.Item;
import backend.jangbogoProject.item.domain.Market;
import backend.jangbogoProject.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> findAllByItemSerialNum(int itemSerialNum)
    {
        return itemRepository.findAllByItemSerialNum(itemSerialNum);
    }

    public List<Item> findAllBySearch(String search)
    {
        return itemRepository.findAllBySearch(search);
    }

    public List<Item> findAllByMarketSerialNum(int marketSerialNum)
    {
        return itemRepository.findAllByMarketSerialNum(marketSerialNum);
    }

    public List<Item> findAllByLowestPriceInGu(int marketGuCode)
    {
        return itemRepository.findAllByLowestPriceInGu(marketGuCode);
    }

    public List<Market> findAllByMarketsInGu(int marketGuCode)
    {
        List<Market> marketList = new ArrayList<>();
        List<Item> itemList = itemRepository.findAllByMarketsInGu(marketGuCode);
        for (int i = 0; i < itemList.size(); i++) {
            Market market = new Market(itemList.get(i).getMarketGuCode(), itemList.get(i).getMarketSerialNum(), itemList.get(i).getMarketName());
            marketList.add(market);
        }
        return marketList;
    }
}
