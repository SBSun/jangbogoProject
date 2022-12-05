package backend.jangbogoProject.controller;

import backend.jangbogoProject.dto.ItemInfo;
import backend.jangbogoProject.repository.ItemInfoRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@RestController
public class HomeController {
    @Autowired
    private ItemInfoRepository itemInfoRepository;

    @GetMapping("/")
    @ResponseBody
    public ModelAndView home()
    {
        ModelAndView mav = new ModelAndView("index");
        String result = "";

        try {
            URL url = new URL("http://openapi.seoul.go.kr:8088/736c497a7462797539316141576a42/json/ListNecessariesPricesService/1/1000/");
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject ListNecessariesPricesService = (JSONObject)jsonObject.get("ListNecessariesPricesService");
            Long totalCount=(Long)ListNecessariesPricesService.get("list_total_count");

            JSONObject itemResult = (JSONObject)ListNecessariesPricesService.get("RESULT");
            JSONArray infoArr = (JSONArray) ListNecessariesPricesService.get("row");

            for(int i=0;i<infoArr.size();i++){
                JSONObject tmp = (JSONObject)infoArr.get(i);
                ItemInfo infoObj = new ItemInfo(i+(long)1, (double)tmp.get("M_SEQ"),(double)tmp.get("A_SEQ"),(String)tmp.get("A_UNIT"),
                        (String)tmp.get("A_PRICE"), (String)tmp.get("M_GU_CODE"),(String)tmp.get("P_DATE"));
                itemInfoRepository.save(infoObj);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @PostMapping("/")
    public String load_save(Model model){
        String result = "";

        try {
            URL url = new URL("http://openapi.seoul.go.kr:8088/736c497a7462797539316141576a42/json/ListNecessariesPricesService/1/1000/");
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject CardSubwayStatsNew = (JSONObject)jsonObject.get("CardSubwayStatsNew");
            Long totalCount=(Long)CardSubwayStatsNew.get("list_total_count");

            JSONObject subResult = (JSONObject)CardSubwayStatsNew.get("RESULT");
            JSONArray infoArr = (JSONArray) CardSubwayStatsNew.get("row");

            for(int i=0;i<infoArr.size();i++){
                JSONObject tmp = (JSONObject)infoArr.get(i);
                ItemInfo infoObj = new ItemInfo(i+(long)1, (int)tmp.get("M_SEQ"),(int)tmp.get("A_SEQ"),(String)tmp.get("A_UNIT"),
                        (String) tmp.get("A_PRICE"), (String)tmp.get("M_GU_CODE"),(String)tmp.get("P_DATE"));
                System.out.println(infoObj);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return "/";
    }
}
