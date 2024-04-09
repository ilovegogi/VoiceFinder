package com.ilovegogi.VoiceFinder.global.oauth2.userinfo;

import java.util.Map;

public class GoogleOAuth2UserInfo implements OAuth2UserInfo {
    private Map<String, Object> attributes; // OAuth2에서 제공하는 사용자 정보 맵

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "google";
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
        return (String) attributes.get("picture");
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getNameAttributeKey() {
        return "sub";
    }
}


