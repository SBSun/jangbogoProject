package backend.jangbogoProject.repository;

import backend.jangbogoProject.entity.User;

public interface UserRepositoryCustom {
    User findByEmail(String email);

    Long deleteByEmail(String email);
}
