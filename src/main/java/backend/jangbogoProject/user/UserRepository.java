package backend.jangbogoProject.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT * FROM user WHERE user_id = ?1", nativeQuery = true)
    UserInfo findByUserId(String id);

    @Query(value = "SELECT user_id, password, name, address FROM user", nativeQuery = true)
    List<UserInfo> findAllUser();
}
