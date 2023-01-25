package backend.jangbogoProject.commodity;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commodity")
public class CommodityController {
    private final CommodityService commodityService;

    @GetMapping("/getCommodityListFromGu")
    private List<CommodityInfoProjection> getCommodityListFromGu(int gu_id){
        return commodityService.getCommodityListFromGu(gu_id);
    }
}
