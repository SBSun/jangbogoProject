package backend.jangbogoProject.calldibs;

import backend.jangbogoProject.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CallDibs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="calldibs_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "email")
    private Member member;
    @Column(name="P_SEQ")
    private int serialNum; // 일련번호

    @Builder
    public CallDibs(int id, int serialNum) {
        this.id = id;
        this.serialNum = serialNum;
    }
}
