package backend.jangbogoProject.oauth;

import backend.jangbogoProject.constant.Authority;
import backend.jangbogoProject.constant.LoginType;
import backend.jangbogoProject.constant.SocialType;
import backend.jangbogoProject.entity.user.User;
import backend.jangbogoProject.oauth.usefinfo.GoogleOAuth2UserInfo;
import backend.jangbogoProject.oauth.usefinfo.KakaoOAuth2UserInfo;
import backend.jangbogoProject.oauth.usefinfo.NaverOAuth2UserInfo;
import backend.jangbogoProject.oauth.usefinfo.OAuth2UserInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private String nameAttributeKey;       // OAuth2 로그인 진행 시 키가 되는 필드 값, PK와 같은 의미
    private OAuth2UserInfo oAuth2UserInfo; // 소셜 타입별 로그인 유저 정보

    @Builder
    public OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oAuth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    public static OAuthAttributes of(SocialType socialType, String userNameAttributeName, Map<String, Object> attributes){

        if(socialType == SocialType.NAVER){
            return ofNaver(userNameAttributeName, attributes);
        }
        if(socialType == SocialType.KAKAO){
            return ofKakao(userNameAttributeName, attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oAuth2UserInfo(new NaverOAuth2UserInfo(attributes))
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oAuth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oAuth2UserInfo(new GoogleOAuth2UserInfo(attributes))
                .build();
    }

    public User toEntity(OAuth2UserInfo oauth2UserInfo){
        return User.builder()
                .email(oauth2UserInfo.getEmail())
                .name(oauth2UserInfo.getNickname())
                .authority(Authority.USER.getValue())
                .loginType(LoginType.Social.name())
                .build();
    }
}
