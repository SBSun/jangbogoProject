package backend.jangbogoProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CategoryController {

    // http://localhost:8080/category/fruit
    @GetMapping("/category/{type}")
    public ModelAndView category(@PathVariable String type)
    {
        ModelAndView mav = new ModelAndView("category/" + type);
        return mav;
    }
}
