package com.ilovegogi.VoiceFinder.global.oauth2.service;

import com.ilovegogi.VoiceFinder.domain.user.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.ilovegogi.VoiceFinder.domain.user.repository.UserRepository;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.global.oauth2.userinfo.OAuth2UserInfo;
import com.ilovegogi.VoiceFinder.global.oauth2.userinfo.OAuth2UserInfoFactory;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("oAuth2User = " + oAuth2User);
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
                userRequest.getClientRegistration().getRegistrationId(),
                oAuth2User.getAttributes());

        Optional<User> userOptional = userRepository.findByEmail(userInfo.getEmail());
        User user;

        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            // 신규 사용자 등록
            user = registerNewUser(userInfo, userRequest.getClientRegistration().getRegistrationId());
        }

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().name())),
                userInfo.getAttributes(),
                userInfo.getNameAttributeKey());
    }

    private User registerNewUser(OAuth2UserInfo userInfo, String provider) {
        User user = new User();
        user.setProvider(provider);
        user.setProviderId(userInfo.getId());
        user.setUsername(userInfo.getName());
        user.setEmail(userInfo.getEmail());
        user.setImageUrl(userInfo.getImageUrl());
        user.setRole(Role.USER); // or any default role
        return userRepository.save(user);
    }
}
