package backend.jangbogoProject.entity.user.controller;

import backend.jangbogoProject.dto.DataResponseDTO;
import backend.jangbogoProject.entity.user.dto.UserRequestDto;
import backend.jangbogoProject.entity.user.dto.UserResponseDto;
import backend.jangbogoProject.entity.user.User;
import backend.jangbogoProject.exception.errorCode.CommonErrorCode;
import backend.jangbogoProject.exception.exception.RestApiException;
import backend.jangbogoProject.security.PrincipalDetails;
import backend.jangbogoProject.entity.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserResponseDto.Info> findAllUser(){
        return userService.findAllUser();
    }

    @GetMapping("/user/checkEmail")
    public boolean checkEmail(@RequestParam @NotBlank @Email String email) {

        return userService.checkEmail(email);
    }

    @PostMapping("/user/signUpUser")
    public DataResponseDTO<UserResponseDto.Info> signUpUser(@RequestBody @Valid UserRequestDto.SignUp signUp) {
        if(signUp == null)
            throw new RestApiException(CommonErrorCode.INVALID_PARAMETER);

        return DataResponseDTO.of(userService.signUp(signUp));
    }

    @PatchMapping("/user/edit")
    public ResponseEntity<String> editUser(@RequestBody @Valid UserRequestDto.Edit edit, @AuthenticationPrincipal PrincipalDetails principalDetails){
        userService.editUser(edit, principalDetails.getUser().getEmail());

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
    public UserResponseDto.LoginSuccessInfo login(@RequestBody @Valid UserRequestDto.Login login) {

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
    public UserResponseDto.TokenInfo reissue(@RequestBody @Valid UserRequestDto.Reissue reissue){
        UserResponseDto.TokenInfo tokenInfo = userService.reissue(reissue);
        return tokenInfo;
    }

    @PostMapping("/user/logout")
    public String logout(HttpServletRequest request) {
        return userService.logout(request);
    }
}
