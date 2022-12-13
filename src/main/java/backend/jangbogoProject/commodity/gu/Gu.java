package backend.jangbogoProject.commodity.gu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class Gu {
    @Id
    @Column(name = "gu_id")
    private Integer id;
    @Column(nullable = false)
    private String name;

    @Builder
    public Gu(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
