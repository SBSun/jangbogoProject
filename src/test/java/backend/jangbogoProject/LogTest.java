package backend.jangbogoProject;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class LogTest {
    @Test
    public void logTest(){
        String value1 = "1번값";
        String value2 = "2번값";
        log.info("1번의 값은 : " + value1 + " 2번의 값은 : "+value2);
        log.info("1번의 값은 : {} 2번의 값은 : {}", value1, value2);
    }
}
