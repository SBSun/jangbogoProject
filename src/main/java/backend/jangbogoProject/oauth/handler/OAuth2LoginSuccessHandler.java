package backend.jangbogoProject.oauth.handler;

import backend.jangbogoProject.entity.user.dto.UserResponseDto;
import backend.jangbogoProject.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {

        log.debug("OAuth2 Login 성공");

        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();

        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.createToken(authentication);
        String targetUrl = UriComponentsBuilder.fromUriString("/member/redirect")
                .queryParam(jwtTokenProvider.getAccessHeader(), tokenInfo.getAccessToken())
                .build().toUriString();
         getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
