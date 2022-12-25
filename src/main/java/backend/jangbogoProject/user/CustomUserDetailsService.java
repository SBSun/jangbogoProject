package backend.jangbogoProject.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String id) {
        User user = userRepository.findByUserId(id);

        if (user != null) {
            // USER 라는 역할을 넣어준다.
            User authUser = User.builder()
                    .id(user.getId())
                    .password(user.getPassword())
                    .name(user.getName())
                    .address(user.getAddress())
                    .authority(user.getAuthority()).build();
            return authUser;
        } else {
            throw new UsernameNotFoundException("can not find User : " + id);
        }
    }
}
