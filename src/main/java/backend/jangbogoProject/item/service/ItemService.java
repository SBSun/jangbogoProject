package backend.jangbogoProject.item.service;

import backend.jangbogoProject.category.CategoryRepository;
import backend.jangbogoProject.item.domain.Item;
import backend.jangbogoProject.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

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

    public List<Item> findAllByCategoryInGu(int marketGuCode, String branch){
        return itemRepository.findAllByCategoryInGu(marketGuCode, branch);
    }

    public List<Item> findAllByCallDibs(String email){
        return itemRepository.findAllByCallDibs(email);
    }
}
