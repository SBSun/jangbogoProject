package backend.jangbogoProject.serviceTest;

import backend.jangbogoProject.commodity.gu.GuInfoProjection;
import backend.jangbogoProject.commodity.gu.GuResponseDto;
import backend.jangbogoProject.commodity.gu.GuService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuServiceTest {

    @Autowired
    GuService guService;

    @Test
    public void findAllGuInfo(){
        GuResponseDto.GuInfoList guInfoList = guService.findAllGuInfo();

        for(GuInfoProjection guInfoProjection : guInfoList.getGuInfoList()){
            System.out.println(guInfoProjection.getGu_Id() + ", " + guInfoProjection.getName());
        }
    }
}
