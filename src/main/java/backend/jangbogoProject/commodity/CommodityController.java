package backend.jangbogoProject.commodity;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commodity")
public class CommodityController {
    private final CommodityService commodityService;

    @GetMapping("/findCommoditiesInGu")
    private List<CommodityInfoProjection> findCommoditiesInGu(int gu_id){
        return commodityService.findCommoditiesInGu(gu_id);
    }

    @GetMapping("/findCategoryInGu")
    private List<CommodityInfoProjection> findCategoryInGu(int gu_id, String category_name){
        return commodityService.findCategoryInGu(gu_id, category_name);
    }

    @PostMapping("/load_save")
    public String load_save(){
        return commodityService.load_save();
    }
}
