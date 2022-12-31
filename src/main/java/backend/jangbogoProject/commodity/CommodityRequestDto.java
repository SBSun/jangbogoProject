package backend.jangbogoProject.commodity;

import backend.jangbogoProject.dto.BasicResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class CommodityRequestDto {
/*
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Info {
        private int id;
        private int M_SEQ;
        private int A_SEQ;
        private String A_UNIT;
        private String A_PRICE;
        private String P_DATE;

        public Commodity toEntity(){
            Commodity commodity = Commodity.builder()
                    .id(id)
                    .m_SEQ(M_SEQ)
                    .a_SEQ(A_SEQ)
                    .a_UNIT(A_UNIT)
                    .a_PRICE(A_PRICE)
                    .p_DATE(P_DATE)
                    .build();
            return commodity;
        }
    }
*/
    /*
    @Getter
    @Setter
    public static class Request {
        private int id;
        private int M_SEQ;
        private int A_SEQ;
        private String A_UNIT;
        private String A_PRICE;
        private String P_DATE;
    }*/

    @Getter
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Response {
        private String M_NAME;
        private String A_NAME;
        private String A_UNIT;
        private String A_PRICE;
        private String M_GU_NAME;
        private BasicResponse response;
    }
}
