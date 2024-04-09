package com.ilovegogi.VoiceFinder.global.oauth2.userinfo;

import java.util.Map;

public class NaverOAuth2UserInfo implements OAuth2UserInfo {
    private Map<String, Object> attributes; // OAuth2에서 제공하는 사용자 정보 맵

    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        // 'response' 키 아래 있는 사용자 정보를 저장
        this.attributes = (Map<String, Object>) attributes.get("response");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getId() {
        return (String) attributes.get("id");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getImageUrl() {
        // 사용자 프로필 이미지 URL 반환
        return (String) attributes.get("profile_image");
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
