package backend.jangbogoProject.user;

import backend.jangbogoProject.commodity.CommodityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserDto.TokenInfo login(UserDto.LoginRequest loginInfo) {
        UserDto.TokenInfo tokenInfo = userService.login(loginInfo);
        return tokenInfo;
    }

    @GetMapping("/signUpUser")
    public ModelAndView signUpUserForm()
    {
        ModelAndView mav = new ModelAndView("signUp");

        return mav;
    }

    @PostMapping("/signUpUser")
    public ModelAndView signUpUser(UserDto.SignUpRequest signUpInfo)
    {
        System.out.println("signUpUser : " + signUpInfo.getId());
        userService.signUp(signUpInfo, Authority.USER);

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
    public ModelAndView signUpAdmin(UserDto.SignUpRequest signUpInfo)
    {
        userService.signUp(signUpInfo, Authority.ADMIN);

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
