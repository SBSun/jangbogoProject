package backend.jangbogoProject.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserResponseDto.Info> findAllUser(){
        return userService.findAllUser();
    }

    @GetMapping("/user/checkEmail")
    public boolean checkEmail(String email){
        return userService.checkEmail(email);
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
    public String logout(@RequestBody UserRequestDto.Logout logout) {
        return userService.logout(logout);
    }
}
