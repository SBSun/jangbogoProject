package backend.jangbogoProject.entity.gu.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

public class GuResponseDto {

    @Getter
    @AllArgsConstructor
    public static class Info{
        private Long guId;
        private String name;

        public String toString(){
            return guId + ", " + name + ", " + guId;
        }
    }
}
