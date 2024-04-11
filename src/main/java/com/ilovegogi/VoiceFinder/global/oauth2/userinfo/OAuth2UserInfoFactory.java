package com.ilovegogi.VoiceFinder.global.oauth2.userinfo;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String provider, Map<String, Object> attributes) {
        switch (provider) {
            case "google":
                return new GoogleOAuth2UserInfo(attributes);
            case "kakao":
                return new KakaoOAuth2UserInfo(attributes);
            case "naver":
                return new NaverOAuth2UserInfo(attributes);
            default:
                throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}

