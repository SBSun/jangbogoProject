package backend.jangbogoProject.entity.commodity.service;


import backend.jangbogoProject.aop.ExecutionTimeChecker;
import backend.jangbogoProject.entity.market.service.MarketService;
import backend.jangbogoProject.exception.errorCode.CommonErrorCode;
import backend.jangbogoProject.exception.exception.RestApiException;
import backend.jangbogoProject.entity.commodity.repository.CommodityRepository;
import backend.jangbogoProject.entity.category.dto.CategoryResponseDTO;
import backend.jangbogoProject.entity.commodity.Commodity;
import backend.jangbogoProject.entity.market.Market;
import backend.jangbogoProject.dto.SearchRequestDTO;
import backend.jangbogoProject.entity.commodity.dto.CommodityInfoProjection;
import backend.jangbogoProject.entity.commodity.dto.CommodityResponseDto;
import backend.jangbogoProject.entity.Page;
import backend.jangbogoProject.entity.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
    public CommodityResponseDto.InfoList getCommodities(int gu_id, SearchRequestDTO searchRequestDTO){

        int startIndex = searchRequestDTO.getOffset();
        int recordSize = searchRequestDTO.getRecordSize();

        int totalDataCnt = commodityRepository.getCommodityCnt(gu_id);

        if(totalDataCnt == 0)
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        Page page = new Page(searchRequestDTO, totalDataCnt);

        List<CommodityResponseDto.Info> list
                = commodityRepository.getCommodities(gu_id, startIndex, recordSize);

        return new CommodityResponseDto.InfoList(list, page.toResponse());
    }

    public List<CommodityInfoProjection> getLowestPriceCommodities(int gu_id){

        List<CommodityInfoProjection> lowestPriceCommodities = commodityRepository.getLowestPriceCommodities(gu_id);

        if(lowestPriceCommodities.isEmpty())
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        return lowestPriceCommodities;
    }

    public CommodityResponseDto.CommodityInfoList findByKeyword(int gu_id, SearchRequestDTO searchRequestDTO){
        String keyword = searchRequestDTO.getKeyword();
        int startIndex = searchRequestDTO.getOffset();
        int recordSize = searchRequestDTO.getRecordSize();

        int totalDataCnt = commodityRepository.findByKeywordCnt(gu_id, keyword);
        if(totalDataCnt == 0)
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        Page page = new Page(searchRequestDTO, totalDataCnt);

        List<CommodityInfoProjection> list
                = commodityRepository.findByKeyword(gu_id, keyword, startIndex, recordSize);

        return new CommodityResponseDto.CommodityInfoList(list, page.toResponse());
    }

    public CommodityResponseDto.CommodityInfoList findByMarket(SearchRequestDTO searchRequestDTO){
        int market_id = Integer.parseInt(searchRequestDTO.getKeyword());
        int startIndex = searchRequestDTO.getOffset();
        int recordSize = searchRequestDTO.getRecordSize();

        int totalDataCnt = commodityRepository.findByMarketCnt(market_id);
        if(totalDataCnt == 0)
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        Page page = new Page(searchRequestDTO, totalDataCnt);

        List<CommodityInfoProjection> list
                = commodityRepository.findByMarket(market_id, startIndex, recordSize);

        return new CommodityResponseDto.CommodityInfoList(list, page.toResponse());
    }

    public CommodityResponseDto.CommodityInfoList findByCategory(int gu_id, SearchRequestDTO searchRequestDTO){
        String keyword = searchRequestDTO.getKeyword();
        int startIndex = searchRequestDTO.getOffset();
        int recordSize = searchRequestDTO.getRecordSize();

        CategoryResponseDTO category = categoryService.findByName(keyword);

        List<CommodityInfoProjection> list;
        int totalDataCnt;

        if(category.getDepth() == 1){
            list = commodityRepository.findByParentCategory(gu_id, category.getId(), startIndex, recordSize);
            totalDataCnt = commodityRepository.findByParentCategoryCnt(gu_id, category.getId());
        }else{
            list = commodityRepository.findByChildCategory(gu_id, category.getId(), startIndex, recordSize);
            totalDataCnt = commodityRepository.findByChildCategoryCnt(gu_id, category.getId());
        }

        if(totalDataCnt == 0)
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        Page page = new Page(searchRequestDTO, totalDataCnt);

        return new CommodityResponseDto.CommodityInfoList(list, page.toResponse());
    }

    public void truncateCommodity(){
        commodityRepository.truncateCommodity();
    }

    @Transactional
    public String load_save(){
        commodityRepository.truncateCommodity();

        String result = "";

        int maxCommodityCount = 5000;
        int start = 1, end = 1000, cycle;

        cycle = maxCommodityCount / 1000;
        maxCommodityCount %= 1000;
        if(maxCommodityCount > 0)
            cycle++;

        try {
            while(cycle > 0){
                URL url = new URL("http://openapi.seoul.go.kr:8088/736c497a7462797539316141576a42/json/ListNecessariesPricesService/"+start+"/"+end+"///2023-03/");
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
                    String a_unit = (String)tmp.get("A_UNIT");
                    String a_price = (String)tmp.get("A_PRICE");
                    if(Integer.parseInt(a_price) == 0)
                        continue;
                    String add_col = (String)tmp.get("ADD_COL");
                    String p_date = (String)tmp.get("P_DATE");

                    if(a_name.contains("("))
                        a_name = a_name.substring(0, a_name.indexOf("("));

                    if(a_name.contains("조기"))
                        a_name = "조기";
                    if(a_name.contains("호박"))
                        a_name = "애호박";

                    int category_id = categoryService.findIdByName(a_name);

                    System.out.println(a_name);
                    Commodity commodity = Commodity.builder()
                            .id(start + i)
                            .m_SEQ(m_seq.intValue())
                            .category_id(category_id)
                            .a_UNIT(a_unit)
                            .a_PRICE(a_price)
                            .add_COL(add_col)
                            .p_DATE(p_date)
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
