package com.ilovegogi.VoiceFinder.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilovegogi.VoiceFinder.domain.user.entity.Role;
import com.ilovegogi.VoiceFinder.global.exception.ErrorCode;
import com.ilovegogi.VoiceFinder.global.redis.RedisUtil;
import com.ilovegogi.VoiceFinder.global.response.ApiResponse;
import com.ilovegogi.VoiceFinder.global.security.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j(topic = "JWT 검증 및 인가")
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final ObjectMapper mapper = new ObjectMapper();


    public JwtAuthorizationFilter(JwtUtil jwtUtil, RedisUtil redisUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.redisUtil = redisUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = jwtUtil.resolveToken(req);
        try {
            if (accessToken != null && jwtUtil.validateToken(accessToken)) {
//                System.out.println("accessToken = " + accessToken);
                // Access Token이 유효한 경우 바로 사용자 인증 정보를 설정
                String email = jwtUtil.getUserInfoFromToken(accessToken).getSubject();
                setAuthentication(email);
            }
        } catch (ExpiredJwtException expiredEx) {
            // Access Token이 만료된 경우
            String email = expiredEx.getClaims().getSubject();
            String storedRefreshToken = jwtUtil.resolveRefreshToken(redisUtil.getRefreshToken(email));
//            System.out.println("storedRefreshToken = " + storedRefreshToken);

            // storedRefreshToken이 null인 경우
            if (storedRefreshToken == null) {
                System.out.println("Refresh token 만료");
                ApiResponse apiResponse = ApiResponse.of(ErrorCode.EXPIRED_JWT_TOKEN.getCode(), ErrorCode.EXPIRED_JWT_TOKEN.getMessage(), "다시 로그인 해주세요.");
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");
                res.getWriter().write(mapper.writeValueAsString(apiResponse));
                return;
            }

            if (jwtUtil.validateToken(storedRefreshToken)) {
                // Refresh Token이 유효한 경우 새로운 Access Token 발급
                Long id = Long.parseLong(expiredEx.getClaims().get("identify").toString());
                Role role = Role.valueOf((String) expiredEx.getClaims().get("auth"));
                String newAccessToken = jwtUtil.createAccessToken(id, email, role);
//                System.out.println("newAccessToken = " + newAccessToken);
                // 응답 헤더에 새로운 Access Token 추가
                res.setHeader(JwtUtil.AUTHORIZATION_HEADER, newAccessToken);
                // 인증 객체 설정
                setAuthentication(email);
            }
        }

        filterChain.doFilter(req, res);
    }

    // 인증 처리
    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    // 인증 객체 생성
    private Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}