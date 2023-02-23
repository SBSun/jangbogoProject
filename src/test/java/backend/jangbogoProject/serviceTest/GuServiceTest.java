package backend.jangbogoProject.serviceTest;

import backend.jangbogoProject.dto.GuInfoProjection;
import backend.jangbogoProject.service.GuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GuServiceTest {

    @Autowired
    GuService guService;

    @Test
    public void findAllGuInfo(){
        List<GuInfoProjection> guInfoList = guService.findAllGuInfo();

        for(GuInfoProjection guInfoProjection : guInfoList){
            System.out.println(guInfoProjection.getGu_Id() + ", " + guInfoProjection.getName());
        }
    }
}
