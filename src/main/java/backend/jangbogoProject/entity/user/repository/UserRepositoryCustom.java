package backend.jangbogoProject.entity.user.repository;

import backend.jangbogoProject.entity.user.User;

public interface UserRepositoryCustom {
    User findByEmail(String email);

    Long deleteByEmail(String email);
}
