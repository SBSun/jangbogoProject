package backend.jangbogoProject.entity.user.controller;

import backend.jangbogoProject.dto.DataResponseDTO;
import backend.jangbogoProject.entity.user.dto.UserRequestDto;
import backend.jangbogoProject.entity.user.dto.UserResponseDto;
import backend.jangbogoProject.entity.user.User;
import backend.jangbogoProject.exception.errorCode.CommonErrorCode;
import backend.jangbogoProject.exception.errorCode.UserErrorCode;
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

    @GetMapping("/users/check")
    public boolean checkEmail(@RequestParam @NotBlank @Email String email) {

        return userService.checkEmail(email);
    }

    @PostMapping("/users")
    public UserResponseDto.Info signUpUser(@RequestBody @Valid UserRequestDto.SignUp signUp) {

        return userService.signUp(signUp);
    }

    @PatchMapping("/users")
    public UserResponseDto.Info editUser(@RequestBody @Valid UserRequestDto.Edit edit, @AuthenticationPrincipal PrincipalDetails principalDetails){
        if(principalDetails.getUser() == null)
            throw new RestApiException(UserErrorCode.INACTIVE_USER);

        return userService.editUser(edit, principalDetails.getUser().getEmail());
    }

    @DeleteMapping("/users")
    public void deleteUser(HttpServletRequest request, @AuthenticationPrincipal PrincipalDetails principalDetails){
        if(principalDetails.getUser() == null)
            throw new RestApiException(UserErrorCode.INACTIVE_USER);

        userService.logout(request);
        userService.deleteUser(principalDetails.getUsername());
    }

    @PostMapping("/users/login")
    public UserResponseDto.LoginSuccessInfo login(@RequestBody @Valid UserRequestDto.Login login) {

        return userService.login(login);
    }

    @GetMapping("/users/loginUser")
    public UserResponseDto.LoginUserInfo getLoginUser(@AuthenticationPrincipal PrincipalDetails principalDetails){
        User user = principalDetails.getUser();

        if(user == null)
            throw new RestApiException(UserErrorCode.INACTIVE_USER);

        String email = user.getEmail();
        String name = user.getName();
        String loginType = user.getLoginType();

        UserResponseDto.LoginUserInfo loginUserInfo = new UserResponseDto.LoginUserInfo(email, name, loginType);
        return loginUserInfo;
    }

    @PostMapping("/users/reissue")
    public UserResponseDto.TokenInfo reissue(@RequestBody @Valid UserRequestDto.Reissue reissue){
        UserResponseDto.TokenInfo tokenInfo = userService.reissue(reissue);
        return tokenInfo;
    }

    @PostMapping("/users/logout")
    public String logout(HttpServletRequest request) {
        return userService.logout(request);
    }
}
