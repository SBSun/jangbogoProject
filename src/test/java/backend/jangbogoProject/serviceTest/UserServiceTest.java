package backend.jangbogoProject.serviceTest;

import backend.jangbogoProject.user.UserResponseDto;
import backend.jangbogoProject.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void getUserInfo(){
        UserResponseDto.Info info = userService.getUserInfo("SBS");

        System.out.println(info);
    }
}
