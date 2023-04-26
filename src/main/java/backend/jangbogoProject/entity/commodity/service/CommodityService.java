package backend.jangbogoProject.entity.commodity.service;


import backend.jangbogoProject.aop.ExecutionTimeChecker;
import backend.jangbogoProject.entity.market.service.MarketService;
import backend.jangbogoProject.exception.errorCode.CommonErrorCode;
import backend.jangbogoProject.exception.exception.RestApiException;
import backend.jangbogoProject.entity.commodity.repository.CommodityRepository;
import backend.jangbogoProject.entity.category.dto.CategoryResponseDto;
import backend.jangbogoProject.entity.commodity.Commodity;
import backend.jangbogoProject.entity.market.Market;
import backend.jangbogoProject.entity.commodity.dto.CommodityResponseDto;
import backend.jangbogoProject.entity.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommodityService {

    private final CommodityRepository commodityRepository;
    private final MarketService marketService;
    private final CategoryService categoryService;

    @ExecutionTimeChecker
    public Page<CommodityResponseDto.Info> getCommodities(Long guId, Pageable pageable){

        return commodityRepository.getCommodities(guId, pageable);
    }

    public List<CommodityResponseDto.Info> getLowestPriceCommodities(Long guId){

        List<CommodityResponseDto.Info> lowestPriceCommodities = commodityRepository.getLowestPriceCommodities(guId);

        if(lowestPriceCommodities.isEmpty())
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        return lowestPriceCommodities;
    }

    public Page<CommodityResponseDto.Info> findByKeyword(Long guId, String keyword, Pageable pageable){

        return commodityRepository.findByKeyword(guId, keyword, pageable);
    }

    public Page<CommodityResponseDto.Info> findByMarket(Long marketId, Pageable pageable){

        return commodityRepository.findByMarket(marketId, pageable);
    }

    /*
    public Page<CommodityResponseDto.Info> findByCategory(Long guId, String categoryName, Pageable pageable){

        CategoryResponseDto.Info category = categoryService.findByName(categoryName);

        Page<CommodityResponseDto.Info> infoList;

        if(category.getDepth() == 1){
            infoList = commodityRepository.findByParentCategory(guId, category.getId(), pageable);
        }else{
            infoList = commodityRepository.findByChildCategory(guId, category.getId(), pageable);
        }

        return infoList;
    }

    // 매달 수요일 오전 6시에 실행
    @Scheduled(cron = "0 0 6 ? * 3")
    @Transactional
    public void getCommodityData(){
        commodityRepository.truncate();

        List<String> categoryNames = categoryService.findNamesByDepth(2);
        String result = "";

        int maxCommodityCount = 16000;
        int start = 1, end = 1000, cycle;

        cycle = maxCommodityCount / 1000;
        maxCommodityCount %= 1000;
        if(maxCommodityCount > 0)
            cycle++;

        try {
            while(cycle > 0){
                URL url = new URL("http://openapi.seoul.go.kr:8088/736c497a7462797539316141576a42/json/ListNecessariesPricesService/"+start+"/"+end+"///2023-04/");
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

                    String a_price = (String)tmp.get("A_PRICE");
                    if(Integer.parseInt(a_price) == 0)
                        continue;

                    String a_name = (String)tmp.get("A_NAME");

                    if(a_name.isEmpty())
                        continue;

                    a_name = a_name.substring(0, a_name.indexOf(" "));
                    if(a_name.contains("("))
                        a_name = a_name.substring(0, a_name.indexOf("("));
                    if(a_name.contains("조기"))
                        a_name = "조기";
                    if(a_name.contains("호박"))
                        a_name = "애호박";
                    if(a_name.contains("계란"))
                        a_name = "달걀";

                    boolean isExist = false;
                    for(String name : categoryNames){
                        if(a_name.equals(name)){
                            isExist = true;
                            break;
                        }
                    }
                    if(!isExist)
                        continue;

                    System.out.println("name : " + a_name);

                    Double m_seq = (Double)tmp.get("M_SEQ");
                    String gu_code = (String)tmp.get("M_GU_CODE");
                    if(!marketService.existsById(m_seq.longValue())){
                        Market market = Market.builder()
                                .id(m_seq.longValue())
                                .name((String)tmp.get("M_NAME"))
                                .gu_id(Long.parseLong(gu_code))
                                .build();

                        marketService.save(market, (String)tmp.get("M_GU_NAME"));
                    }

                    String a_unit = (String)tmp.get("A_UNIT");
                    String add_col = (String)tmp.get("ADD_COL");
                    String p_date = (String)tmp.get("P_DATE");

                    Long category_id = categoryService.findIdByName(a_name);

                    Commodity commodity = Commodity.builder()
                            .id((long)start + i)
                            .m_SEQ(m_seq.longValue())
                            .category_id(category_id)
                            .a_UNIT(a_unit)
                            .a_PRICE(a_price)
                            .add_COL(add_col)
                            .p_DATE(p_date)
                            .build();

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
    }*/
}
