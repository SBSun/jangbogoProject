package backend.jangbogoProject.controller;

import backend.jangbogoProject.constant.StatusCode;
import backend.jangbogoProject.dto.ResponseDTO;
import backend.jangbogoProject.dto.UserRequestDto;
import backend.jangbogoProject.dto.UserResponseDto;
import backend.jangbogoProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseDTO signUpUser(@RequestBody UserRequestDto.SignUp signUp){
        userService.signUp(signUp);

        return ResponseDTO.of(StatusCode.OK);
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
    public UserResponseDto.LoginInfo login(@RequestBody UserRequestDto.Login login) {

        return userService.login(login);
    }

    @GetMapping("/user/getLoginUserEmail")
    public ResponseEntity<String> getLoginUserEmail(){
        String loginUserEmail = userService.getLoginUserEmail().get();
        System.out.println("loginUserEmail : " + loginUserEmail);
        if(loginUserEmail.equals("anonymousUser"))
            return new ResponseEntity<>("로그인 상태가 아닙니다.", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(loginUserEmail, HttpStatus.OK);
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
