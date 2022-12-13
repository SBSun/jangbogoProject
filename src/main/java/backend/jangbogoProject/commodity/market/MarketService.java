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

    public void save(Market _market, Gu _gu){
        Optional<Gu> gu = guRepository.findById(_gu.getId());

        if(!gu.isPresent())
            guRepository.save(_gu);

        marketRepository.save(_market);
    }
}
