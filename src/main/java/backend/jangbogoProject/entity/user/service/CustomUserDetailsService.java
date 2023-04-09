package backend.jangbogoProject.entity.user.service;

import backend.jangbogoProject.entity.user.User;
import backend.jangbogoProject.entity.user.repository.UserRepository;
import backend.jangbogoProject.security.PrincipalDetails;
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
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            // USER 라는 역할을 넣어준다.
            return new PrincipalDetails(user);
        } else {
            throw new UsernameNotFoundException("can not find User : " + email);
        }
    }
}
