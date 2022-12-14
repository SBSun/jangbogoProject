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
        if(user != null){
            UserDto.Info info = UserDto.Info.builder()
                    .id(id)
                    .password(user.getPassword())
                    .name(user.getName())
                    .address(user.getAddress())
                    .build();

            response = new UserDto.Response(info, 200, "success");
        }
        else
            response = new UserDto.Response(null, 400, "Bad Request");

        return  response;
    }

    @Transactional
    // 트랜잭션 안에서 데이터베이스의 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태가 된다.
    //이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 변경된 데이터를 데이터베이스에 반영해준다.
    public void updateInfo(UserDto.Info info){
        User user = userRepository.findByUserId(info.getId());

        if(user == null)
            new IllegalArgumentException("해당 회원은 존재하지 않습니다.");

        String encPassword = passwordEncoder.encode(info.getPassword());

        user.update(encPassword, info.getName(), info.getAddress());
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
