package backend.jangbogoProject.oauth.usefinfo;

import java.util.Map;

public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes){
        this.attributes = attributes;
    }

    // 소셜 식별 값 : 구글 - "email", 카카오 - "email", 네이버 - "email"
    public abstract String getEmail();

    public abstract String getNickname();
}
