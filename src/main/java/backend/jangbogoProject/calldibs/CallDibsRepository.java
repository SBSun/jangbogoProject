package backend.jangbogoProject.calldibs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CallDibsRepository extends JpaRepository<CallDibs, Integer> {
    Optional<CallDibs> findByEmailAndSerialNum(String email, int serialNum);

    Boolean existsByEmailAndSerialNum(String email, int serialNum);
}
