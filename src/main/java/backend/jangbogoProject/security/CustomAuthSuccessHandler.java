package backend.jangbogoProject.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("Login Success");

        List<String> roleNames = new ArrayList<>();

        // 사용자가 가진 모든 권한을 문자열로 체크한다!
        // 사용자는 여러 권한을 가질 수 있다.
        authentication.getAuthorities().forEach(authority->{
            roleNames.add(authority.getAuthority());
        });

        System.out.println("ROLE NAMES : "+ roleNames);

        if(roleNames.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin");
            return;
        }
        else if(roleNames.contains("ROLE_USER")) {
            response.sendRedirect("/");
            return;
        }

        response.sendRedirect("/");
    }
}
