package backend.jangbogoProject.commodity.market;

import backend.jangbogoProject.commodity.gu.Gu;
import backend.jangbogoProject.commodity.gu.GuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MarketService {
    @Autowired
    private MarketRepository marketRepository;
    @Autowired
    private GuRepository guRepository;

    public Market findById(int id){
        return marketRepository.findById(id).get();
    }

    public boolean existsById(int id){
        return marketRepository.existsById(id);
    }

    public void save(Market _market, String guName){
        if(!guRepository.existsById(_market.getGu_id())){
            Gu gu = Gu.builder()
                    .id(_market.getGu_id())
                    .name(guName)
                    .build();
            guRepository.save(gu);
        }

        marketRepository.save(_market);
    }
}
