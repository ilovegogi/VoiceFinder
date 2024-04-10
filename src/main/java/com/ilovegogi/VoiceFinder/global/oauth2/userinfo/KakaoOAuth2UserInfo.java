package com.ilovegogi.VoiceFinder.global.oauth2.userinfo;

import java.util.Map;

public class KakaoOAuth2UserInfo implements OAuth2UserInfo {
    private Map<String, Object> attributes; // OAuth2에서 제공하는 사용자 정보 맵
    Map<String, Object> kakaoAccount;


    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.kakaoAccount =  (Map<String, Object>) attributes.get("kakao_account");
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return (String) kakaoAccount.get("email");
    }

    @Override
    public String getName() {
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        return (String) profile.get("nickname");
    }
    @Override
    public String getImageUrl() {
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        return (String) profile.get("thumbnail_image_url");
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getNameAttributeKey() {
        return "id";
    }
}



