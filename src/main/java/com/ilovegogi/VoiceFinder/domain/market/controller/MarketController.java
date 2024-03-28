package com.ilovegogi.VoiceFinder.domain.market.controller;

import com.ilovegogi.VoiceFinder.domain.market.dto.MarketRegistrationRequestDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketRegistrationResponseDto;
import com.ilovegogi.VoiceFinder.domain.market.service.MarketService;
import com.ilovegogi.VoiceFinder.global.response.ApiResponse;
import com.ilovegogi.VoiceFinder.global.response.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/markets")
public class MarketController {

    private final MarketService marketService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponse> registrationMarket(@Valid @RequestBody MarketRegistrationRequestDto marketRegistrationRequestDto) {
        MarketRegistrationResponseDto marketRegistrationResponseDto = marketService.registrationMarket(marketRegistrationRequestDto);
        SuccessCode successCode = SuccessCode.SUCCESS_MARKET_CREATE;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), marketRegistrationResponseDto));
    }
}
