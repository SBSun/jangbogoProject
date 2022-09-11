package backend.jangbogoProject.calldibs;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CallDibsRepository extends JpaRepository<CallDibs, Integer> {
    Boolean existsByEmailAndSerialNum(String email, int serialNum);
}
