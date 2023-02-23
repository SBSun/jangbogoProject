package backend.jangbogoProject.entity;

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
    @Column(nullable = false, name = "category_id")
    private int category_id;
    @Column(nullable = false, name = "unit")
    private String A_UNIT;
    @Column(nullable = false, name = "price")
    private String A_PRICE;
    @Column(name = "remarks")
    private String ADD_COL;
    @Column(nullable = false)
    private String P_DATE;

    @Builder
    public Commodity(int id, int m_SEQ, int category_id, String a_UNIT, String a_PRICE, String add_COL, String p_DATE) {
        this.id = id;
        M_SEQ = m_SEQ;
        this.category_id = category_id;
        A_UNIT = a_UNIT;
        A_PRICE = a_PRICE;
        ADD_COL = add_COL;
        P_DATE = p_DATE;
    }
}
