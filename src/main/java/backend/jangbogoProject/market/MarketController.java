package backend.jangbogoProject.market;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MarketController {
    @Autowired
    private MarketService marketService;

    @GetMapping("/marketsInGu")
    public String getMarketsInGu(@RequestParam int guCode)
    {
        List<Market> marketList = marketService.findAllByMarketsInGu(guCode);

        Gson gson = new Gson();
        String listJson = gson.toJson(marketList, List.class).toString();
        return listJson;
    }
}
