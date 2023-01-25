package backend.jangbogoProject.commodity.market;

import backend.jangbogoProject.dto.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketController {
    private final MarketService marketService;

    @GetMapping("/findMarketsInGu")
    private MarketResponseDto.MarketInfoList findMarketsInGu(int gu_id){
        List<MarketInfoProjection> list = marketService.findMarketsInGu(gu_id);

        String message;
        int state;

        if(list.isEmpty()){
            message = "데이터가 존재하지 않습니다.";
            state = 404;
        }else{
            message = "데이터 반환 성공";
            state = 200;
        }

        BasicResponse basicResponse = BasicResponse.builder()
                .message(message)
                .state(state)
                .build();

        MarketResponseDto.MarketInfoList marketInfoList = new MarketResponseDto.MarketInfoList(list, basicResponse);

        return marketInfoList;
    }
}
