package com.ilovegogi.VoiceFinder.global.oauth2.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/auth")
public class OAuth2Controller {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientService authorizedClientService;

    public OAuth2Controller(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientService authorizedClientService) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.authorizedClientService = authorizedClientService;
    }

//    @GetMapping("/{provider}/login")
//    public RedirectView redirectToProvider(@PathVariable String provider) {
//        // 클라이언트 등록 정보를 조회합니다.
//        ClientRegistration registration = this.clientRegistrationRepository.findByRegistrationId(provider);
//        // OAuth2 로그인 페이지 URL을 구성합니다.
//        String redirectUrl = OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI + "/" + provider;
//        System.out.println("redirectUrl = " + redirectUrl);
//        return new RedirectView(redirectUrl);
//    }
}

