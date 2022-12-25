package backend.jangbogoProject.jwt;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogoutAccessTokenRepository extends CrudRepository<LogoutAccessToken, String> {
}
