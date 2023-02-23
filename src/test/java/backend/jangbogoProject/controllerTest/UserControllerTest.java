package backend.jangbogoProject.controllerTest;

import backend.jangbogoProject.controller.UserController;
import backend.jangbogoProject.dto.UserRequestDto;
import backend.jangbogoProject.dto.UserResponseDto;
import backend.jangbogoProject.service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @DisplayName("회원 가입 성공")
    @Test
    void signUpSuccess() throws Exception {
        // given
        UserRequestDto.SignUp request = signUpRequest();

        doReturn(userResponse()).when(userService)
                .signUp(any(UserRequestDto.SignUp.class));

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/signUpUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );

        // then
        MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();

        UserResponseDto.Info response = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), UserResponseDto.Info.class);
        assertThat(response.getUser_id()).isEqualTo("test@test.test");
    }

    private UserRequestDto.SignUp signUpRequest(){
        return UserRequestDto.SignUp.builder()
                .email("test@test.test")
                .password("test Password")
                .name("test Name")
                .address("test Address")
                .build();
    }

    private UserResponseDto.Info userResponse(){
        return UserResponseDto.Info.builder()
                .email("test@test.test")
                .password("test Password")
                .name("test Name")
                .address("test Address")
                .build();
    }
}
