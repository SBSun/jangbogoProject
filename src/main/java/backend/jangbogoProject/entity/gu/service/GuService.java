package backend.jangbogoProject.entity.gu.service;

import backend.jangbogoProject.exception.errorCode.CommonErrorCode;
import backend.jangbogoProject.exception.exception.RestApiException;
import backend.jangbogoProject.entity.gu.repository.GuRepository;
import backend.jangbogoProject.entity.gu.dto.GuInfoProjection;
import backend.jangbogoProject.entity.gu.Gu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GuService {

    private final GuRepository guRepository;

    @Transactional
    public void save(Gu gu){
        guRepository.save(gu);
    }

    public Gu findById(int id){
        Gu gu = guRepository.findById(id).get();

        if(gu == null)
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        return gu;
    }

    public boolean existsById(int id){
        return guRepository.existsById(id);
    }

    public String getGuName(int id){
        String guName = guRepository.getGuName(id);

        if(guName.isEmpty())
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        return guName;
    }

    public List<GuInfoProjection> findAllGuInfo(){
        List<GuInfoProjection> guInfoList = guRepository.findAllGuInfo();

        if(guInfoList.isEmpty())
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        return guInfoList;
    }
}
