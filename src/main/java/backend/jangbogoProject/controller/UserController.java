package backend.jangbogoProject.controller;

import backend.jangbogoProject.constant.StatusCode;
import backend.jangbogoProject.dto.DataResponseDTO;
import backend.jangbogoProject.dto.ResponseDTO;
import backend.jangbogoProject.dto.UserRequestDto;
import backend.jangbogoProject.dto.UserResponseDto;
import backend.jangbogoProject.entity.User;
import backend.jangbogoProject.security.PrincipalDetails;
import backend.jangbogoProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<String> deleteUser(HttpServletRequest request, @AuthenticationPrincipal PrincipalDetails principalDetails){

        System.out.println("principalDetails.getUser().getEmail() : " + principalDetails.getUser().getEmail());

        userService.logout(request);
        userService.deleteUser(principalDetails.getUsername());
        return new ResponseEntity<>("회원 탈퇴 성공", HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public UserResponseDto.LoginSuccessInfo login(@RequestBody UserRequestDto.Login login) {

        return userService.login(login);
    }

    @GetMapping("/user/getLoginUser")
    public DataResponseDTO<UserResponseDto.LoginUserInfo> getLoginUser(@AuthenticationPrincipal PrincipalDetails principalDetails){
        User user = principalDetails.getUser();

        String email = user.getEmail();
        String name = user.getName();
        String loginType = user.getLoginType();

        UserResponseDto.LoginUserInfo loginUserInfo = new UserResponseDto.LoginUserInfo(email, name, loginType);

        return DataResponseDTO.of(loginUserInfo);
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
