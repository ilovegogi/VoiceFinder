package com.ilovegogi.VoiceFinder.global.oauth2.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // 여기에 로그인 성공 시의 로직을 구현
        // 예: JWT 토큰 생성 및 반환
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print("{\"message\": \"Login successful\"}");
        response.getWriter().flush();
    }
}
