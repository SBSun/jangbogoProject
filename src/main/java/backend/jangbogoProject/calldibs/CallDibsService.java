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

    public CallDibs save(CallDibs callDibs){
        if(callDibsRepository.existsByEmailAndSerialNum(callDibs.getEmail(), callDibs.getSerialNum())){
            return null;
        }

        callDibsRepository.save(callDibs);
        return callDibs;
    }

    public CallDibs findById(int id){
        return callDibsRepository.findById(id).get();
    }
}
