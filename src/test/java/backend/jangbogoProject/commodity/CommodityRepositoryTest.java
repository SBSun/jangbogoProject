package backend.jangbogoProject.commodity;

import backend.jangbogoProject.repository.CommodityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommodityRepositoryTest {
    @Autowired
    private CommodityRepository commodityRepository;

    @DisplayName("테이블 데이터 삭제")
    @Test
    void truncate() {
        commodityRepository.truncateCommodity();
    }
}