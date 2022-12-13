package backend.jangbogoProject;

import backend.jangbogoProject.commodity.Commodity;
import backend.jangbogoProject.commodity.CommodityRepository;
import backend.jangbogoProject.user.Authority;
import backend.jangbogoProject.user.UserDto;
import backend.jangbogoProject.user.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@RestController
public class HomeController {
    @Autowired
    private CommodityRepository commodityRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView home()
    {
        ModelAndView mav = new ModelAndView("home");

        return mav;
    }

    @GetMapping("/signUp")
    public ModelAndView signUp()
    {
        ModelAndView mav = new ModelAndView("signUp");

        return mav;
    }

    @PostMapping("/create")
    public ModelAndView create(UserDto.Info userInfo)
    {
        userService.save(userInfo.toEntity(), Authority.USER);

        ModelAndView mav = new ModelAndView("home");

        return mav;
    }

    @GetMapping("/signUpAdmin")
    public ModelAndView signUpAdmin()
    {
        ModelAndView mav = new ModelAndView("signUpAdmin");

        return mav;
    }

    @PostMapping("/createAdmin")
    public ModelAndView createAdmin(UserDto.Info userInfo)
    {
        userService.save(userInfo.toEntity(), Authority.ADMIN);

        ModelAndView mav = new ModelAndView("home");

        return mav;
    }

    @GetMapping("/admin")
    public ModelAndView admin()
    {
        ModelAndView mav = new ModelAndView("admin");

        return mav;
    }


    @PostMapping("/data_load_save")
    public ModelAndView data_load_save(){




        ModelAndView mav = new ModelAndView("admin");
        return mav;
    }
}
