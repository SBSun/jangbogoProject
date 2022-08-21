package backend.jangbogoProject.item.controller;

import backend.jangbogoProject.item.domain.Item;
import backend.jangbogoProject.item.service.ItemService;
import backend.jangbogoProject.service.MemberService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/member/search")
    public ModelAndView search(@RequestParam(required = false) String content){

        ModelAndView mav = new ModelAndView("member/search");
        mav.addObject("return", itemService.findAllBySearch(content));
        return mav;
    }

    // http://localhost:8080/category/fruit
    @GetMapping("/category/{type}")
    public ModelAndView category(@PathVariable String type)
    {
        ModelAndView mav = new ModelAndView("category/" + type);
        return mav;
    }
}
