package backend.jangbogoProject;

import backend.jangbogoProject.commodity.CommodityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
    @Autowired
    private CommodityService commodityService;

    @GetMapping("/")
    public ModelAndView home()
    {
        ModelAndView mav = new ModelAndView("home");

        return mav;
    }

    @PostMapping("/data_load_save")
    public ModelAndView data_load_save(){
        commodityService.load_save();

        ModelAndView mav = new ModelAndView("admin");
        return mav;
    }
}
