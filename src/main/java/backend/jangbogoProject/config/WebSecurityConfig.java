package backend.jangbogoProject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration      // 해당 클래스를 Configuration으로 등록한다.
@EnableWebSecurity  // Spring Security를 활성화 시킨다.
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    // 규칙 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // HttpServletRequest에 따라 접근(access)을 제한
                // antMatchers() 메서드로 특정 경로를 지정하며, permitAll(), hasRole() 메서드로 역할(Role)에 따른 접근 설정을 잡아준다.
                .authorizeRequests()
                    .antMatchers(   "/", "/member/login", "/member/register", "/category/**","/css/**", "/js/**", "/File/**").permitAll() // 해당 경로들에 대해서는 권한없이 접근 가능
                    .antMatchers("/logout", "/member/mypage", "/member/editInfo", "/member/favorite").hasRole("MEMBER") // ROLE_MEMBER 권한을 가지고 있는 사용자만 접근 가능
                    //.anyRequest().authenticated()   // 모든 요청에 대해, 인증된 사용자만 접근하도록 설정
                .and()
                // form 기반으로 인증을 하도록 합니다. 로그인 정보는 기본적으로 HttpSession을 이용
                .formLogin()
                    .loginPage("/member/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/") // 로그인 성공 후 redirect 주소
                    .permitAll()
                .and()
                // 로그아웃을 지원하는 메서드이며 HTTP 세션을 제거
                .logout()
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true); // 세션 날리기
        return http.build();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication() // 로그인 관련 처리
                .dataSource(dataSource)
                .usersByUsernameQuery("select email, password, enabled "
                        + "from member "
                        + "where email = ?")
                .authoritiesByUsernameQuery("select m.email, r.name " //권한에 관한 설정
                        + "from member_role mr inner join member m on mr.member_id = m.id "
                        + "inner join role r on mr.role_id = r.id "
                        + "where m.email = ?");
    }

    @Bean PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
