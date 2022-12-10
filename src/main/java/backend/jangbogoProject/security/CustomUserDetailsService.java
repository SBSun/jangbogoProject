package backend.jangbogoProject.security;

import backend.jangbogoProject.user.User;
import backend.jangbogoProject.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String id) {
        User user = userRepository.findOneById(id);

        if (user != null) {
            // USER 라는 역할을 넣어준다.

            User authUser = User.builder()
                    .id(user.getId())
                    .password(user.getPassword())
                    .name(user.getName())
                    .address(user.getAddress())
                    .authority("User").build();
            return authUser;
        } else {
            throw new UsernameNotFoundException("can not find User : " + id);
        }
    }
}
