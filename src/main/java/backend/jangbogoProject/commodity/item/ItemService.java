package backend.jangbogoProject.commodity.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void save(Item item){
        itemRepository.save(item);
    }

    public Item findById(int id){
        Item item = itemRepository.findById(id).get();

        if(item == null)
            new IllegalArgumentException("해당 객체는 존재하지 않습니다.");

        return item;
    }

    public boolean existsById(int id){
        return itemRepository.existsById(id);
    }

    public String getItemName(int id){
        String itemName = itemRepository.getItemName(id);

        if(itemName.isEmpty())
            new IllegalArgumentException("해당 객체는 존재하지 않습니다.");

        return itemName;
    }
}
