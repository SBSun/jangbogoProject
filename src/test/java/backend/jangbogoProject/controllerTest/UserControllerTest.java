package backend.jangbogoProject.controllerTest;

import backend.jangbogoProject.commodity.CommodityService;
import backend.jangbogoProject.user.User;
import backend.jangbogoProject.user.UserController;
import backend.jangbogoProject.user.UserRequestDto;
import backend.jangbogoProject.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;
    @MockBean
    private CommodityService commodityService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "USER")
    void User_SignUp_Test() throws Exception{

        UserRequestDto.SignUp signUp = UserRequestDto.SignUp.builder()
                .id("Test Id")
                .password("1234")
                .address("Test Address")
                .name("Test Name")
                .build();

        String content = objectMapper.writeValueAsString(signUp);

        mvc.perform(
                MockMvcRequestBuilders.post("/user/signUpUser")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andDo(MockMvcResultHandlers.print());
    }
}
