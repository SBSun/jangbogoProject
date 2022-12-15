package backend.jangbogoProject.commodity.gu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuService {

    @Autowired
    private GuRepository guRepository;

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
}
