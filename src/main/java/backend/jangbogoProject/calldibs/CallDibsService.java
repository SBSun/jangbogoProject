package backend.jangbogoProject.calldibs;

import backend.jangbogoProject.market.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class CallDibsService {
    private final CallDibsRepository callDibsRepository;

    public void save(CallDibs callDibs){
        callDibsRepository.save(callDibs);
    }
}
