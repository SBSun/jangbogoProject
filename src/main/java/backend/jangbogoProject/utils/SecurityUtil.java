package backend.jangbogoProject.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SecurityUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtil.class);

    private SecurityUtil(){

    }

    public static Optional<String> getCurrentUserEmail(){
        // Security Context에 Authentication 객체가 저장되는 기점은 JwtFilter의 doFilter 메서드에서 request가 들어올 때이다.
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            LOGGER.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String userEmail = null;

        if(authentication.getPrincipal() instanceof UserDetails){
            UserDetails user = (UserDetails)authentication.getPrincipal();
            userEmail = user.getUsername();
        }else if(authentication.getPrincipal() instanceof String){ // 로그인 하지 않은 사용자의 return 값은 "anonymousUser"
            userEmail = (String)authentication.getPrincipal();
        }

        return Optional.ofNullable(userEmail);
    }
}
