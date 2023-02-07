package backend.jangbogoProject.commodity;


import backend.jangbogoProject.category.CategoryResponseDTO;
import backend.jangbogoProject.category.CategoryService;
import backend.jangbogoProject.commodity.market.Market;
import backend.jangbogoProject.commodity.market.MarketService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommodityService {

    private final CommodityRepository commodityRepository;
    private final MarketService marketService;
    private final CategoryService categoryService;

    public List<CommodityInfoProjection> findCommoditiesInGu(int gu_id){
        List<CommodityInfoProjection> list = commodityRepository.findCommoditiesInGu(gu_id);

        return list;
    }

    public List<CommodityInfoProjection> findSearchInGu(int gu_id, String find){
        List<CommodityInfoProjection> list = commodityRepository.findSearchInGu(gu_id, find);

        return list;
    }

    public List<CommodityInfoProjection> findCategoryInGu(int gu_id, String category_name){
        CategoryResponseDTO category = categoryService.getCategoryByName(category_name);

        List<CommodityInfoProjection> list;

        if(category.getDepth() == 1){
            list = commodityRepository.findParentCategoryInGu(gu_id, category.getId());
        }else{
            list = commodityRepository.findChildCategoryInGu(gu_id, category.getId());
        }

        return list;
    }

    public void truncateCommodity(){
        commodityRepository.truncateCommodity();
    }

    public String load_save(){
        String result = "";

        int maxCommodityCount = 2000;
        int start = 1, end = 1000, cycle;

        cycle = maxCommodityCount / 1000;
        maxCommodityCount %= 1000;
        if(maxCommodityCount > 0)
            cycle++;

        try {
            while(cycle > 0){
                URL url = new URL("http://openapi.seoul.go.kr:8088/736c497a7462797539316141576a42/json/ListNecessariesPricesService/"+start+"/"+end+"/");
                BufferedReader bf;
                bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
                result = bf.readLine();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
                JSONObject ListNecessariesPricesService = (JSONObject)jsonObject.get("ListNecessariesPricesService");
                Long totalCount=(Long)ListNecessariesPricesService.get("list_total_count");

                JSONObject commodityResult = (JSONObject)ListNecessariesPricesService.get("RESULT");
                JSONArray infoArr = (JSONArray) ListNecessariesPricesService.get("row");

                for(int i=0; i<infoArr.size(); i++){
                    JSONObject tmp = (JSONObject)infoArr.get(i);

                    Double m_seq = (Double)tmp.get("M_SEQ");
                    String a_name = (String)tmp.get("A_NAME");

                    if(a_name.contains("("))
                        a_name = a_name.substring(0, a_name.indexOf("("));

                    if(a_name.contains("조기"))
                        a_name = "조기";
                    if(a_name.contains("호박"))
                        a_name = "애호박";

                    System.out.println(a_name);
                    Commodity commodity = Commodity.builder()
                            .id(start + i)
                            .m_SEQ(m_seq.intValue())
                            .category_id(categoryService.getCategoryIdByName(a_name).intValue())
                            .a_UNIT((String)tmp.get("A_UNIT"))
                            .a_PRICE((String)tmp.get("A_PRICE"))
                            .add_COL((String)tmp.get("ADD_COL"))
                            .p_DATE((String)tmp.get("P_DATE"))
                            .build();

                    String gu_code = (String)tmp.get("M_GU_CODE");
                    if(!marketService.existsById(commodity.getM_SEQ())){
                        Market market = Market.builder()
                                .id(commodity.getM_SEQ())
                                .name((String)tmp.get("M_NAME"))
                                .gu_id(Integer.parseInt(gu_code))
                                .build();

                        marketService.save(market, (String)tmp.get("M_GU_NAME"));
                    }

                    commodityRepository.save(commodity);
                }

                cycle--;

                start += 1000;

                if(cycle == 1)
                {
                    if(maxCommodityCount % 1000 == 0)
                        end = start + 1000 - 1;
                    else
                        end = start + (maxCommodityCount % 1000) - 1;
                }
                else
                    end = start + 1000 - 1;
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
