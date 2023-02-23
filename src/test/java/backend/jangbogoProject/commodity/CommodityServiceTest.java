package backend.jangbogoProject.commodity;

import backend.jangbogoProject.repository.CommodityRepository;
import backend.jangbogoProject.service.CommodityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommodityServiceTest {
    @InjectMocks
    private CommodityService commodityService;

    @Mock
    private CommodityRepository commodityRepository;

    @DisplayName("공공데이터 불러와서 DB에 저장")
    @Test
    void load_save(){

    }

    @DisplayName("테이블 데이터 삭제")
    @Test
    void truncate(){
        doNothing().when(commodityRepository)
                .truncateCommodity();

        commodityService.truncateCommodity();

        verify(commodityRepository, times(1)).truncateCommodity();
    }
}
