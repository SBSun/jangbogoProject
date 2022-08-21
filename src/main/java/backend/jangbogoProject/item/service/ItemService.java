package backend.jangbogoProject.item.service;

import backend.jangbogoProject.item.domain.Item;
import backend.jangbogoProject.item.repository.ItemRepository;
import backend.jangbogoProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Item> findAllByItemName(String itemName)
    {
        return itemRepository.findAllByItemName(itemName);
    }

    public List<Item> findAllByMarketSerialNum(int marketSerialNum)
    {
        return itemRepository.findAllByMarketSerialNum(marketSerialNum);
    }

    public List<Item> findAllByMarketGuCode(int marketGuCode)
    {
        return itemRepository.findAllByMarketGuCode(marketGuCode);
    }
}
