package backend.jangbogoProject.entity.user.repository;

import backend.jangbogoProject.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Boolean existsByEmail(String email);
}
