package backend.jangbogoProject.controller;

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
         String result = "";

        try {
            URL url = new URL("http://openapi.seoul.go.kr:8088/736c497a7462797539316141576a42/json/ListNecessariesPricesService/1/1000/");
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject ListNecessariesPricesService = (JSONObject)jsonObject.get("ListNecessariesPricesService");
            Long totalCount=(Long)ListNecessariesPricesService.get("list_total_count");

            JSONObject commodityResult = (JSONObject)ListNecessariesPricesService.get("RESULT");
            JSONArray infoArr = (JSONArray) ListNecessariesPricesService.get("row");

            for(int i=0;i<infoArr.size();i++){
                JSONObject tmp = (JSONObject)infoArr.get(i);
                Commodity commodity = new Commodity(i+(long)1, (double)tmp.get("M_SEQ"),(double)tmp.get("A_SEQ"),(String)tmp.get("A_UNIT"),
                        (String)tmp.get("A_PRICE"), (String)tmp.get("P_DATE"));
                commodityRepository.save(commodity);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        ModelAndView mav = new ModelAndView("admin");
        return mav;
    }
}
