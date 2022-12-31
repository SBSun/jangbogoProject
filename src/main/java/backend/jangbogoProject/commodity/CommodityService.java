package backend.jangbogoProject.commodity;

import backend.jangbogoProject.commodity.gu.GuService;
import backend.jangbogoProject.commodity.item.Item;
import backend.jangbogoProject.commodity.item.ItemService;
import backend.jangbogoProject.commodity.market.Market;
import backend.jangbogoProject.commodity.market.MarketService;
import backend.jangbogoProject.dto.BasicResponse;
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
    private final ItemService itemService;
    private final MarketService marketService;
    private final GuService guService;

    public CommodityResponseDto.CommodityInfoList getCommodityListFromGu(int gu_id){
        List<CommodityInfoProjection> list = commodityRepository.getCommodityListFromGu(gu_id);

        BasicResponse basicResponse = new BasicResponse(HttpStatus.OK.value(), "상품 리스트 반환 성공");
        CommodityResponseDto.CommodityInfoList commodityInfoList =
                new CommodityResponseDto.CommodityInfoList(list, basicResponse);

        return commodityInfoList;
    }

    public void load_save(){
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

            JSONObject commodityResult = (JSONObject)ListNecessariesPricesService.get("RESULT");
            JSONArray infoArr = (JSONArray) ListNecessariesPricesService.get("row");

            for(int i=0;i<infoArr.size();i++){
                JSONObject tmp = (JSONObject)infoArr.get(i);

                Double m_seq = (Double)tmp.get("M_SEQ");
                Double a_seq = (Double)tmp.get("A_SEQ");


                Commodity commodity = Commodity.builder()
                        .id(i + 1)
                        .m_SEQ(m_seq.intValue())
                        .a_SEQ(a_seq.intValue())
                        .a_UNIT((String)tmp.get("A_UNIT"))
                        .a_PRICE((String)tmp.get("A_PRICE"))
                        .p_DATE((String)tmp.get("P_DATE"))
                        .build();

                if(!itemService.existsById(commodity.getA_SEQ())){
                    Item item = Item.builder()
                            .id(commodity.getA_SEQ())
                            .name((String)tmp.get("A_NAME"))
                            .build();

                    itemService.save(item);
                }

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

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
