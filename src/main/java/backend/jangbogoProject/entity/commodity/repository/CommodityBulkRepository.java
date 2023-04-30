package backend.jangbogoProject.entity.commodity.repository;

import backend.jangbogoProject.entity.commodity.Commodity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommodityBulkRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveAll(List<Commodity> commodities){
        String sql = "INSERT INTO commodity (market_id, category_id, unit, price, remarks, p_date) " +
                "VALUES(?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Commodity commodity = commodities.get(i);
                        ps.setLong(1, commodity.getM_SEQ());
                        ps.setLong(2, commodity.getCategory_id());
                        ps.setString(3, commodity.getA_PRICE());
                        ps.setString(4, commodity.getA_PRICE());
                        ps.setString(5, commodity.getADD_COL());
                        ps.setString(6, commodity.getP_DATE());
                    }

                    @Override
                    public int getBatchSize() {
                        return 1000;
                    }
                }
        );
    }
}
