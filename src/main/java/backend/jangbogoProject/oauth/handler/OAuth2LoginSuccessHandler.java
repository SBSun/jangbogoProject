package backend.jangbogoProject.oauth.handler;

import backend.jangbogoProject.dto.UserResponseDto;
import backend.jangbogoProject.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {

        log.debug("OAuth2 Login 성공");

        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();

        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.createToken(authentication);
        response.addHeader(jwtTokenProvider.getAccessHeader(), jwtTokenProvider.BEARER + tokenInfo.getAccessToken());

        jwtTokenProvider.sendAccessAndRefreshToken(response, tokenInfo.getAccessToken(), tokenInfo.getRefreshToken());
    }
}
