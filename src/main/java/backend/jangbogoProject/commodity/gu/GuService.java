package backend.jangbogoProject.commodity.gu;

import backend.jangbogoProject.dto.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuService {

    private final GuRepository guRepository;

    public void save(Gu gu){
        guRepository.save(gu);
    }

    public Gu findById(int id){
        Gu gu = guRepository.findById(id).get();

        if(gu == null)
            new IllegalArgumentException("해당 객체는 존재하지 않습니다.");

        return gu;
    }

    public boolean existsById(int id){
        return guRepository.existsById(id);
    }

    public String getGuName(int id){
        String guName = guRepository.getGuName(id);

        if(guName.isEmpty())
            new IllegalArgumentException("해당 객체는 존재하지 않습니다.");

        return guName;
    }

    public GuResponseDto.GuInfoList findAllGuInfo(){
        List<GuInfoProjection> guInfoList = guRepository.findAllGuInfo();

        int state;
        String message;

        if(guInfoList.isEmpty()){
            state = 404;
            message = "데이터가 존재하지 않습니다.";
        }else{
            state = 200;
            message = "데이터 반환 성공";
        }

        BasicResponse basicResponse = new BasicResponse(state,message);

        return new GuResponseDto.GuInfoList(guInfoList, basicResponse);
    }
}
