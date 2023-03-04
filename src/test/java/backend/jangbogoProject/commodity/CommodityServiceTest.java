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

}
