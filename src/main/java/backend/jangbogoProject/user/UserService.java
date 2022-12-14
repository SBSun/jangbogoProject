package backend.jangbogoProject.user;

import backend.jangbogoProject.user.User;
import backend.jangbogoProject.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //회원가입
    public User save(User newUser, Authority authority)
    {
        User user = userRepository.findByUserId(newUser.getId());

        if(user != null){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.setAuthority(authority.getValue());
        userRepository.save(newUser);
        return newUser;
    }

    public UserDto.Response findById(String id){
        User user = userRepository.findByUserId(id);

        UserDto.Response response;

        if(user != null)
        {
            UserDto.Info info = UserDto.Info.builder()
                    .id(id)
                    .password(user.getPassword())
                    .name(user.getName())
                    .address(user.getAddress())
                    .build();

            response = new UserDto.Response(info, 200, "success");

        }
        else
        {
            response = new UserDto.Response(null, 400, "Bad Request");
        }

        return  response;
    }

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
