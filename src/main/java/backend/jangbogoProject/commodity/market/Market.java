package backend.jangbogoProject.commodity.market;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class Market {
    @Id
    @Column(name = "market_id")
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(name = "gu_id", nullable = false)
    private int gu_id;

    @Builder
    public Market(int id, String name, int gu_id) {
        this.id = id;
        this.name = name;
        this.gu_id = gu_id;
    }
}
