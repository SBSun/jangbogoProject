package backend.jangbogoProject.user;

import backend.jangbogoProject.commodity.CommodityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
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
