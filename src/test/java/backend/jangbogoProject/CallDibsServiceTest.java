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
        callDibsDTO.setSerialNum(177848);

        CallDibs saveCallDibs = callDibsService.save(callDibsDTO.toEntity());
        if(saveCallDibs == null){
            System.out.println("이미 찜 목록에 등록되어 있는 품목");
        }else{
            System.out.println("찜 등록 성공");
        }
    }

    @Test
    void 찜삭제(){
        CallDibsDTO callDibsDTO = new CallDibsDTO();
        callDibsDTO.setEmail("byung0216@naver.com");
        callDibsDTO.setSerialNum(177848);

        CallDibs deleteCallDibs = callDibsService.delete(callDibsDTO.toEntity());
        if(deleteCallDibs == null){
            System.out.println("찜 목록에 존재하지 않는 품목입니다.");
        }else{
            System.out.println("찜 삭제 성공");
        }
    }
}
