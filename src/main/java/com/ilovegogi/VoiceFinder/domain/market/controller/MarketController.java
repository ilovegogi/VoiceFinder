package com.ilovegogi.VoiceFinder.domain.market.controller;

import com.ilovegogi.VoiceFinder.domain.market.dto.MarketListResponseDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketRegistrationRequestDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketRegistrationResponseDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketResponseDto;
import com.ilovegogi.VoiceFinder.domain.market.service.MarketService;
import com.ilovegogi.VoiceFinder.global.response.ApiResponse;
import com.ilovegogi.VoiceFinder.global.response.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getMarketList() {
        List<MarketListResponseDto> marketListResponseDtos = marketService.getMarketList();
        SuccessCode successCode = SuccessCode.SUCCESS_GET_ALL_MARKET;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), marketListResponseDtos));
    }

}
