package com.ilovegogi.VoiceFinder.domain.market.controller;

import com.ilovegogi.VoiceFinder.domain.market.dto.MarketListResponseDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketRegistrationRequestDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketRegistrationResponseDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketResponseDto;
import com.ilovegogi.VoiceFinder.domain.market.service.MarketService;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.global.response.ApiResponse;
import com.ilovegogi.VoiceFinder.global.response.SuccessCode;
import com.ilovegogi.VoiceFinder.global.s3.FileUploadService;
import com.ilovegogi.VoiceFinder.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/markets")
public class MarketController {

    private final MarketService marketService;
    private final FileUploadService fileUploadService;

    /*
    @PostMapping("/new")
    public ResponseEntity<ApiResponse> registrationMarket(@Valid @RequestBody MarketRegistrationRequestDto marketRegistrationRequestDto) {
        MarketRegistrationResponseDto marketRegistrationResponseDto = marketService.registrationMarket(marketRegistrationRequestDto);
        SuccessCode successCode = SuccessCode.SUCCESS_MARKET_CREATE;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), marketRegistrationResponseDto));
    }*/

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse> registrationMarket(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                          @RequestPart("post") MarketRegistrationRequestDto marketRegistrationRequestDto,
                                          @RequestPart(value = "imageUrls", required = false) List<MultipartFile> files) throws IOException {
        User user = userDetails.getUser();
        List<String> fileUrls = null;
        if (files != null && !files.isEmpty()) {
            fileUrls = fileUploadService.uploadFiles(files); // 파일 업로드 서비스 호출
        }
        SuccessCode successCode = SuccessCode.SUCCESS_MARKET_CREATE;
        MarketRegistrationResponseDto marketRegistrationResponseDto = marketService.registrationMarket(user, fileUrls, marketRegistrationRequestDto);
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), marketRegistrationResponseDto));
    }

    @GetMapping
    public Page<MarketResponseDto> getMarkets(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "createdAt") String sortBy,
            @RequestParam(name = "isAsc", defaultValue = "true") boolean isAsc) {
        return marketService.getMarkets(page - 1, size, sortBy, isAsc, keyword);
    }


    @GetMapping("/{id}")
    public MarketResponseDto getMarketDto(@PathVariable Long id) {
        return marketService.getMarketDto(id);
    }


    /*@GetMapping("/list")
    public ResponseEntity<ApiResponse> getMarketList() {
        List<MarketListResponseDto> marketListResponseDtos = marketService.getMarketList();
        SuccessCode successCode = SuccessCode.SUCCESS_GET_ALL_MARKET;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), marketListResponseDtos));
    }*/

}
