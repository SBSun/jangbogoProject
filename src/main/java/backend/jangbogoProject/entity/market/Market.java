package backend.jangbogoProject.entity.market;

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
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "gu_id", nullable = false)
    private Long gu_id;

    @Builder
    public Market(Long id, String name, Long gu_id) {
        this.id = id;
        this.name = name;
        this.gu_id = gu_id;
    }
}
