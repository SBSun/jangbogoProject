package backend.jangbogoProject.user;

import backend.jangbogoProject.commodity.CommodityService;
import backend.jangbogoProject.dto.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final CommodityService commodityService;
    private final UserService userService;

    @PostMapping("/login")
    public UserResponseDto.TokenInfo login(UserRequestDto.Login login) {
        UserResponseDto.TokenInfo tokenInfo = userService.login(login);
        return tokenInfo;
    }

    @PostMapping("/reissue")
    public UserResponseDto.TokenInfo reissue(@RequestBody UserRequestDto.Reissue reissue){
        UserResponseDto.TokenInfo tokenInfo = userService.reissue(reissue);
        return tokenInfo;
    }

    @PostMapping("/logout")
    public BasicResponse logout(@RequestBody UserRequestDto.Logout logout) {
        return userService.logout(logout);
    }

    @GetMapping("/signUpUser")
    public ModelAndView signUpUserForm()
    {
        ModelAndView mav = new ModelAndView("signUp");

        return mav;
    }

    @PostMapping("/signUpUser")
    public ModelAndView signUpUser(UserRequestDto.SignUp signUp)
    {
        System.out.println("signUpUser : " + signUp.getId());
        userService.signUp(signUp, Authority.USER);

        ModelAndView mav = new ModelAndView("home");

        return mav;
    }

    @GetMapping("/signUpAdmin")
    public ModelAndView signUpAdminForm()
    {
        ModelAndView mav = new ModelAndView("signUpAdmin");

        return mav;
    }

    @PostMapping("/signUpAdmin")
    public ModelAndView signUpAdmin(UserRequestDto.SignUp signUp)
    {
        userService.signUp(signUp, Authority.ADMIN);

        ModelAndView mav = new ModelAndView("home");

        return mav;
    }

    @GetMapping("/admin")
    public ModelAndView adminForm()
    {
        ModelAndView mav = new ModelAndView("admin");

        return mav;
    }
}
