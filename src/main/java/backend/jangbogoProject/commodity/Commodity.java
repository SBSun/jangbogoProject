package backend.jangbogoProject.commodity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commodity_id")
    private int id;
    @Column(nullable = false, name = "market_id")
    private int M_SEQ;
    @Column(nullable = false, name = "unit")
    private String A_UNIT;
    @Column(nullable = false, name = "price")
    private String A_PRICE;
    @Column(nullable = false)
    private String P_DATE;

    @Builder
    public Commodity(int id, int m_SEQ, String a_UNIT, String a_PRICE, String p_DATE) {
        this.id = id;
        M_SEQ = m_SEQ;
        A_UNIT = a_UNIT;
        A_PRICE = a_PRICE;
        P_DATE = p_DATE;
    }
}
