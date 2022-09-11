package backend.jangbogoProject;

import backend.jangbogoProject.calldibs.CallDibs;
import backend.jangbogoProject.calldibs.CallDibsDTO;
import backend.jangbogoProject.calldibs.CallDibsRepository;
import backend.jangbogoProject.calldibs.CallDibsService;
import backend.jangbogoProject.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CallDibsServiceTest {
    @Autowired
    CallDibsService callDibsService;
    @Autowired
    CallDibsRepository callDibsRepository;

    @Test
    void 찜등록(){
        CallDibsDTO callDibsDTO = new CallDibsDTO();
        callDibsDTO.setEmail("byung0216@naver.com");
        callDibsDTO.setSerialNum(1778848);

        CallDibs saveCallDibs = callDibsDTO.toEntity();

        int saveId = callDibsService.save(saveCallDibs).getId();

        CallDibs findCallDibs = callDibsService.findById(saveId);
        System.out.println(findCallDibs.getEmail());
    }
}
