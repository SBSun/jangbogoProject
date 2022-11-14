package backend.jangbogoProject.service;

import backend.jangbogoProject.domain.CallDibs;
import backend.jangbogoProject.repository.CallDibsRepository;
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

    public CallDibs delete(CallDibs callDibs){
        CallDibs deleteCallDibs = callDibsRepository.findByEmailAndSerialNum(callDibs.getEmail(), callDibs.getSerialNum())
                .orElseThrow(() -> new IllegalArgumentException("찜 목록에 존재하지 않은 품목입니다."));
        callDibsRepository.deleteById(deleteCallDibs.getId());
        return callDibs;
    }

    public CallDibs findById(int id){
        return callDibsRepository.findById(id).get();
    }

    public CallDibs findByEmailAndSerialNum(String email, int serialNum){
        CallDibs callDibs = callDibsRepository.findByEmailAndSerialNum(email, serialNum)
                .orElseThrow(() -> new IllegalArgumentException("찜 목록에 존재하지 않은 품목입니다."));

        return callDibs;
    }
}
