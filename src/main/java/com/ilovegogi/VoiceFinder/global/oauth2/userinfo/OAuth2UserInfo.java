package com.ilovegogi.VoiceFinder.global.oauth2.userinfo;

import java.util.Map;

public interface OAuth2UserInfo {

    String getProvider();
    String getId();
    String getEmail();
    String getName();
    String getImageUrl();
    Map<String, Object> getAttributes(); // 속성 맵을 반환하는 메소드 추가

    String getNameAttributeKey();
}



