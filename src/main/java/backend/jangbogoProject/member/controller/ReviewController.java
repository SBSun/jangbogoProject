package backend.jangbogoProject.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ReviewController {

    // http://localhost:8080/review/market
    @GetMapping("/review/market")
    public ModelAndView market(){
        ModelAndView mav = new ModelAndView("review/market");
        return mav;
    }

    // http://localhost:8080/review/user
    @GetMapping("/review/user")
    public ModelAndView userReview(){
        ModelAndView mav = new ModelAndView("review/user");
        return mav;
    }

    // http://localhost:8080/review/write
    @GetMapping("/review/write")
    public ModelAndView write(){
        ModelAndView mav = new ModelAndView("review/write");
        return mav;
    }
}
