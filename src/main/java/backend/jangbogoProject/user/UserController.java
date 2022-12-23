package backend.jangbogoProject.user;

import backend.jangbogoProject.commodity.CommodityService;
import backend.jangbogoProject.jwt.TokenDto;
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

    private CommodityService commodityService;
    private UserService userService;

    @PostMapping("/login")
    public TokenDto login(UserDto.LoginRequest userLoginRequestDto) {
        String memberId = userLoginRequestDto.getId();
        String password = userLoginRequestDto.getPassword();

        TokenDto tokenDto = userService.login(memberId, password);
        return tokenDto;
    }

    @GetMapping("/signUpUser")
    public ModelAndView signUpUserForm()
    {
        ModelAndView mav = new ModelAndView("signUp");

        return mav;
    }

    @PostMapping("/signUpUser")
    public ModelAndView signUpUser(UserDto.Info userInfo)
    {
        userService.save(userInfo.toEntity(), Authority.USER);

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
    public ModelAndView signUpAdmin(UserDto.Info userInfo)
    {
        userService.save(userInfo.toEntity(), Authority.ADMIN);

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
