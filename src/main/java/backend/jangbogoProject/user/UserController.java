package backend.jangbogoProject.user;

import backend.jangbogoProject.dto.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public UserResponseDto.InfoList findAllUser(){
        return userService.findALlUser();
    }

    @GetMapping("/user/checkId")
    public boolean checkId(String id){
        return userService.checkId(id);
    }

    @PostMapping("/user/signUpUser")
    public UserResponseDto.Info signUpUser(@RequestBody UserRequestDto.SignUp signUp){
        return userService.signUp(signUp);
    }

    @PostMapping("/user/login")
    public UserResponseDto.TokenInfo login(@RequestBody UserRequestDto.Login login) {
        UserResponseDto.TokenInfo tokenInfo = userService.login(login);
        return tokenInfo;
    }

    @PostMapping("/user/reissue")
    public UserResponseDto.TokenInfo reissue(@RequestBody UserRequestDto.Reissue reissue){
        UserResponseDto.TokenInfo tokenInfo = userService.reissue(reissue);
        return tokenInfo;
    }

    @PostMapping("/user/logout")
    public BasicResponse logout(@RequestBody UserRequestDto.Logout logout) {
        return userService.logout(logout);
    }
}
