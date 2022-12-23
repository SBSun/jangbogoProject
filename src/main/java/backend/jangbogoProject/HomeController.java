package backend.jangbogoProject;

import backend.jangbogoProject.commodity.CommodityService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final CommodityService commodityService;

    @GetMapping("/")
    public ModelAndView homeForm()
    {
        ModelAndView mav = new ModelAndView("index");

        return mav;
    }

    @PostMapping("/data_load_save")
    public ModelAndView data_load_save(){
        commodityService.load_save();

        ModelAndView mav = new ModelAndView("admin");
        return mav;
    }
}
