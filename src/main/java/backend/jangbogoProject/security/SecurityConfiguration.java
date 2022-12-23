package backend.jangbogoProject.security;

import backend.jangbogoProject.jwt.JwtAuthenticationFilter;
import backend.jangbogoProject.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfiguration {
    private final UserDetailsService userDetailsService;
    // 로그인 성공 핸들러 의존성 주입
    private final CustomAuthSuccessHandler customSuccessHandler;
    // 로그인 실패 핸들러 의존성 주입
    private final CustomAuthFailureHandler customFailureHandler;

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        //  AuthenticationManagerBuilder에  패스워드 암호화를 위해 Spring Security에서 제공하는 BCryptPasswordEncoder를 추가
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /* @formatter:off */
        http
                // rest api이므로 basic auth 및 csrf 보안을 사용하지 않는다는 설정이다.
                .httpBasic().disable()
                .csrf().disable()
                // JWT를 사용하기 때문에 세션을 사용하지 않는다는 설정
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/", "/signUp").permitAll() // 설정한 리소스의 접근을 인증절차 없이 허용
                .antMatchers("/admin", "/signUpAdmin", "/data_load_save").hasRole("ADMIN")
                .antMatchers("/signUpAdmin").hasRole("USER")
                .and()
                //  JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행하겠다는 설정이다.
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
            .formLogin()
                .loginPage("/") // 기본 로그인 페이지
                .defaultSuccessUrl("/")
                .failureHandler(customFailureHandler)
                .usernameParameter("id")
                .passwordParameter("password")
                .successHandler(customSuccessHandler)
            .and()
                .logout()
                .permitAll()
                 // 로그아웃 성공 URL (기본 값 : "/login?logout")
                //.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 주소창에 요청해도 포스트로 인식하여 로그아웃
                .deleteCookies("JSESSIONID") // 로그아웃 시 JSESSIONID 제거
                .invalidateHttpSession(true) // 로그아웃 시 세션 종료
                .clearAuthentication(true); // 로그아웃 시 권한 제거

        return http.build();
        /* @formatter:on */
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
