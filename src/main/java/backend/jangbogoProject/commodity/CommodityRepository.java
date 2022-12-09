package backend.jangbogoProject.commodity;

import backend.jangbogoProject.commodity.Commodity;
import backend.jangbogoProject.dto.ItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommodityRepository extends JpaRepository<Commodity, Long> {


}
