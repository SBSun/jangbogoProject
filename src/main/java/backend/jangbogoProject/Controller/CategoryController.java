package backend.jangbogoProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CategoryController {

    // http://localhost:8080/category/fruit
    @GetMapping("/category/fruit")
    public ModelAndView fruit()
    {
        ModelAndView mav = new ModelAndView("category/fruit");
        return mav;
    }

    // http://localhost:8080/category/meat
    @GetMapping("/category/meat")
    public ModelAndView meat()
    {
        ModelAndView mav = new ModelAndView("category/meat");
        return mav;
    }

    // http://localhost:8080/category/seafood
    @GetMapping("/category/seafood")
    public ModelAndView seafood()
    {
        ModelAndView mav = new ModelAndView("category/seafood");
        return mav;
    }

    // http://localhost:8080/category/vegeta
    @GetMapping("/category/vegeta")
    public ModelAndView vegeta()
    {
        ModelAndView mav = new ModelAndView("category/vegeta");
        return mav;
    }
}
