package backend.jangbogoProject.jwt;

import backend.jangbogoProject.dto.BasicResponse;
import backend.jangbogoProject.user.UserDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final String secret;                     // 사용할 알고리즘에 따라 길이를 맞추어 base64로 인코딩 된 임의 값
    public static final String BEARER_TYPE = "Bearer ";
    private static final String AUTHORITIES_KEY = "auth";
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 30 * 60 * 1000L;              // 30분 엑세스 토큰 만료 시간
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;    // 7일  리프레시 토큰 만료 시간
    public static final long REISSUE_EXPIRATION_TIME = 3 * 24 * 60 * 60 * 1000L;      // 3일  재발행 만료 시간
    private Key key;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret){
        this.secret = secret;
        // secret 값을 Base64 Decode해서 key 변수에 할당
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
    // 토큰 추출 메서드. payload를 가져온다.
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    // 해당 토큰이 만료되었는지
    public Boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public String createAccessToken(Authentication authentication){
        return createToken(authentication, ACCESS_TOKEN_EXPIRE_TIME);
    }

    public String createRefreshToken(Authentication authentication){
        return createToken(authentication, REFRESH_TOKEN_EXPIRE_TIME);
    }

    // 유저 정보를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
    public String createToken(Authentication authentication, long expireTime) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expireTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // 토큰 정보 검사
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            LOGGER.info("잘못된 JWT 서명입니다.");
        }catch(ExpiredJwtException e){
            LOGGER.info("만료된 JWT 토큰입니다.");
        }catch(UnsupportedJwtException e){
            LOGGER.info("지원하지 않는 JWT 토큰입니다.");
        }catch(IllegalArgumentException e){
            LOGGER.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
    // 해당 토큰의 남은 시간
    public long getRemainMilliSeconds(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        Date now = new Date();
        return expiration.getTime() - now.getTime();
    }
}
