package backend.jangbogoProject.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PatchMapping("/user/edit")
    public ResponseEntity<String> editUser(@RequestBody UserRequestDto.Edit edit){
        userService.editUser(edit);

        return new ResponseEntity<>("회원 정보 수정 성공", HttpStatus.OK);
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<String> deleteUser(HttpServletRequest request){
        userService.logout(request);
        userService.deleteUser();
        return new ResponseEntity<>("회원 탈퇴 성공", HttpStatus.OK);
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
    public String logout(HttpServletRequest request) {
        return userService.logout(request);
    }
}
