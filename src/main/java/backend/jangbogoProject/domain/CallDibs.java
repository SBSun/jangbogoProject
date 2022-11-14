package backend.jangbogoProject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "calldibs")
public class CallDibs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="calldibs_id")
    private int id;
    @Column(nullable = false)
    private String email;
    @Column(name="P_SEQ")
    private int serialNum; // 일련번호

    @Builder
    public CallDibs(int id, String email, int serialNum) {
        this.id = id;
        this.email = email;
        this.serialNum = serialNum;
    }
}
