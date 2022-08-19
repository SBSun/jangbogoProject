package backend.jangbogoProject.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    // http://localhost:8080
    @GetMapping("/")
    @ResponseBody
    public ModelAndView home()
    {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }


}
