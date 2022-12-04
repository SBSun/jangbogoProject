package backend.jangbogoProject.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class ItemInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double M_SEQ;
    private double A_SEQ;
    private String A_UNIT;
    private String A_PRICE;
    private String M_GU_CODE;
    private String P_DATE;

    public ItemInfo() {
    }

    public ItemInfo(Long id, double m_SEQ, double a_SEQ, String a_UNIT, String a_PRICE, String m_GU_CODE, String p_DATE) {
        this.id = id;
        M_SEQ = m_SEQ;
        A_SEQ = a_SEQ;
        A_UNIT = a_UNIT;
        A_PRICE = a_PRICE;
        M_GU_CODE = m_GU_CODE;
        P_DATE = p_DATE;
    }
}
