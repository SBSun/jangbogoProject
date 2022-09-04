package backend.jangbogoProject.item.controller;

import backend.jangbogoProject.category.Category;
import backend.jangbogoProject.category.CategoryService;
import backend.jangbogoProject.item.domain.Item;
import backend.jangbogoProject.item.service.ItemService;
import com.google.gson.Gson;
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
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/member/search")
    public ModelAndView searchPage(){
        ModelAndView mav = new ModelAndView("member/search");
        return mav;
    }

    @GetMapping("/member/searchContent")
    public String search(@RequestParam(required = false) String content){
        System.out.println("검색한 내용 : " + content);
        List<Item> items = itemService.findAllBySearch(content);

        Gson gson = new Gson();
        String listJson = gson.toJson(items, List.class).toString();
        System.out.println(listJson);
        return listJson;
    }

    @GetMapping("/lowestPriceInGu")
    public String getLowestPriceInGu(@RequestParam int guCode)
    {
        System.out.println("최저가 검색할 구 : " + guCode);
        List<Item> items = itemService.findAllByLowestPriceInGu(guCode);

        Gson gson = new Gson();
        String listJson = gson.toJson(items, List.class).toString();
        System.out.println(listJson);
        return listJson;
    }

    // http://localhost:8080/category/fruit
    @GetMapping("/category")
    public ModelAndView categoryForm()
    {
        ModelAndView mav = new ModelAndView("member/category");
        return mav;
    }

    @GetMapping("/categoryInGu")
    public String getCategoryItems(@RequestParam int guCode, @RequestParam String branch)
    {
        List<Item> items = itemService.findAllByCategoryInGu(guCode, branch);

        Gson gson = new Gson();
        String listJson = gson.toJson(items, List.class).toString();
        System.out.println(listJson);
        return listJson;
    }
}
