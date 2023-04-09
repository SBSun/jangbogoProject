package backend.jangbogoProject.jwt;

import backend.jangbogoProject.entity.user.dto.UserResponseDto;
import backend.jangbogoProject.entity.user.service.CustomUserDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Component
public class JwtTokenProvider {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    private String secret;            // 사용할 알고리즘에 따라 길이를 맞추어 base64로 인코딩 된 임의 값

    @Value("${jwt.access.expiration}")
    private long accessTokenExpirationPeriod = 30 * 60 * 1000L; // 30분 엑세스 토큰 만료 시간

    @Value("${jwt.refresh.expiration}")
    private long refreshTokenExpirationPeriod = 7 * 24 * 60 * 60 * 1000L; // 7일  리프레시 토큰 만료 시간

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    /**
     * JWT의 헤더에 들어오는 값 : 'Authorization(Key) = Bearer {토큰} (Value)' 형식
     */
    public static final String BEARER = "Bearer ";
    private static final String AUTHORITIES_KEY = "auth";

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

    // 유저 정보를 가지고 Token 생성
    public UserResponseDto.TokenInfo createToken(Authentication authentication) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(new Date(now + accessTokenExpirationPeriod))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + refreshTokenExpirationPeriod))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return UserResponseDto.TokenInfo.builder()
                .grantType(BEARER)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .refreshTokenExpirationTime(refreshTokenExpirationPeriod)
                .build();
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
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, accessToken, authorities);
    }

    // 토큰 정보 검사
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            LOGGER.info("잘못된 JWT 서명입니다.");
        }catch(ExpiredJwtException e){
            LOGGER.info("만료된 JWT 토큰입니다. : ");
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

    public Long getExpiration(String accessToken) {
        // accessToken 남은 유효시간
        Date expiration = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody().getExpiration();
        // 현재 시간
        Long now = new Date().getTime();
        return (expiration.getTime() - now);
    }
}
