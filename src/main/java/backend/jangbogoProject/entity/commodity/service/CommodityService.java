package backend.jangbogoProject.entity.commodity.service;


import backend.jangbogoProject.aop.ExecutionTimeChecker;
import backend.jangbogoProject.entity.category.Category;
import backend.jangbogoProject.entity.commodity.repository.CommodityBulkRepository;
import backend.jangbogoProject.entity.gu.Gu;
import backend.jangbogoProject.entity.gu.repository.GuRepository;
import backend.jangbogoProject.entity.gu.service.GuService;
import backend.jangbogoProject.entity.market.repository.MarketRepository;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommodityService {

    private final CommodityRepository commodityRepository;
    private final CommodityBulkRepository commodityBulkRepository;
    private final MarketRepository marketRepository;
    private final GuRepository guRepository;
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


    public Page<CommodityResponseDto.Info> findByCategory(Long guId, String categoryName, Pageable pageable){

        Category category = categoryService.findByName(categoryName);

        Page<CommodityResponseDto.Info> infoList;

        if(category.getDepth() == 1){
            infoList = commodityRepository.findByParentCategory(guId, category.getId(), pageable);
        }else{
            infoList = commodityRepository.findByChildCategory(guId, category.getId(), pageable);
        }

        return infoList;
    }

    // 매달 수요일 오전 6시에 실행
    //@Scheduled(cron = "0 0 6 ? * 3")
    @Scheduled(fixedDelay = 5000000)
    @Transactional
    public void getCommodityData(){

        List<Category> categories = categoryService.findByDepth(2);
        Map<String, Long> categoryMap = new HashMap<>();
        for (int i = 0; i < categories.size(); i++) {
            categoryMap.put(categories.get(i).getName(), categories.get(i).getId());
        }

        List<Long> marketIds = new ArrayList<>();
        List<Long> guIds = new ArrayList<>();
        List<Commodity> commodities = new ArrayList<>();
        List<Market> markets = new ArrayList<>();
        List<Gu> gus = new ArrayList<>();

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

                    Long categoryId = categoryMap.get(a_name);

                    if(categoryId == null)  // 존재하지 않은 카테고리일 경우 continue
                        continue;

                    Long m_seq = ((Double)tmp.get("M_SEQ")).longValue();
                    Long gu_code = Long.parseLong((String)tmp.get("M_GU_CODE"));

                    if(!guIds.contains(gu_code)){
                        guIds.add(gu_code);

                        String guName= (String)tmp.get("M_GU_NAME");
                        Gu gu = Gu.builder()
                                .id(gu_code)
                                .name(guName)
                                .build();

                        gus.add(gu);
                    }

                    if(!marketIds.contains(m_seq)){
                        marketIds.add(m_seq);
                        Market market = Market.builder()
                                .id(m_seq)
                                .name((String)tmp.get("M_NAME"))
                                .gu_id(gu_code)
                                .build();

                        markets.add(market);
                    }

                    String a_unit = (String)tmp.get("A_UNIT");
                    String add_col = (String)tmp.get("ADD_COL");
                    String p_date = (String)tmp.get("P_DATE");

                    Commodity commodity = Commodity.builder()
                            .m_SEQ(m_seq)
                            .category_id(categoryId)
                            .a_UNIT(a_unit)
                            .a_PRICE(a_price)
                            .add_COL(add_col)
                            .p_DATE(p_date)
                            .build();

                    commodities.add(commodity);
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

        commodityRepository.truncate();

        guRepository.saveAll(gus);
        marketRepository.saveAll(markets);
        long startTime = System.currentTimeMillis();
        commodityBulkRepository.saveAll(commodities);

        long stopTime = System.currentTimeMillis();
        System.out.println("JPA saveAll() 적용  : " + (stopTime - startTime));
        commodityRepository.foreignKeyChecksOn();
    }
}
