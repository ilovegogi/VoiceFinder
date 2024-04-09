package com.ilovegogi.VoiceFinder.global.oauth2.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilovegogi.VoiceFinder.domain.user.entity.Role;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.domain.user.repository.UserRepository;
import com.ilovegogi.VoiceFinder.global.jwt.JwtUtil;
import com.ilovegogi.VoiceFinder.global.response.ApiResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.ilovegogi.VoiceFinder.global.response.SuccessCode.SUCCESS_USER_LOGIN;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    public CustomAuthenticationSuccessHandler(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // providerId를 사용하여 User 객체 조회
        String providerId = oAuth2User.getAttribute("id");
        Optional<User> userOptional = userRepository.findByProviderId(providerId); // providerId를 통해 User 객체를 조회하는 메서드
        User user = userOptional.get();


        // JWT 토큰 생성
        String accessToken = jwtUtil.createAccessToken(user.getId(), user.getEmail(), user.getRole());
        String refreshToken = jwtUtil.createRefreshToken(user.getId(), user.getEmail(), user.getRole());

        // Refresh Token을 Redis에 저장
        jwtUtil.storeRefreshToken(user.getEmail(), refreshToken);

        // Access Token을 Client에 반환
        response.addHeader("Authorization", accessToken);

        ApiResponse apiResponse = ApiResponse.of(SUCCESS_USER_LOGIN.getCode(), SUCCESS_USER_LOGIN.getMessage(), user.getEmail());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(mapper.writeValueAsString(apiResponse));
    }
}
