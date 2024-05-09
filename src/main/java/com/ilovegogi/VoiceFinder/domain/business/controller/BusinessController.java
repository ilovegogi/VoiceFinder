package com.ilovegogi.VoiceFinder.domain.business.controller;

import com.ilovegogi.VoiceFinder.domain.business.dto.BusinessSignupRequestDto;
import com.ilovegogi.VoiceFinder.domain.business.dto.BusinessSignupResponseDto;
import com.ilovegogi.VoiceFinder.domain.business.service.BusinessService;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketRegistrationRequestDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketRegistrationResponseDto;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.global.response.ApiResponse;
import com.ilovegogi.VoiceFinder.global.response.SuccessCode;
import com.ilovegogi.VoiceFinder.global.s3.FileUploadService;
import com.ilovegogi.VoiceFinder.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/business")
public class BusinessController {

    private final BusinessService businessService;
    private final FileUploadService fileUploadService;

    /*@PostMapping("/signup")
    public ResponseEntity<ApiResponse> signupForBiz(@Valid @RequestBody BusinessSignupRequestDto businessSignupRequestDto) {
        BusinessSignupResponseDto businessSignupResponseDto = businessService.signupForBiz(businessSignupRequestDto);
        SuccessCode successCode = SuccessCode.SUCCESS_BUSINESS_SIGN_UP;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), businessSignupResponseDto));
    }*/

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse> signupForBiz(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                          @RequestPart("post") BusinessSignupRequestDto businessSignupRequestDto,
                                                          @RequestPart(value = "imageUrl", required = false) MultipartFile file) throws IOException {
        User user = userDetails.getUser();
        String fileUrl = null;
        if (file != null && !file.isEmpty()) {
            fileUrl = fileUploadService.uploadFile(file); // 파일 업로드 서비스 호출
        }
        SuccessCode successCode = SuccessCode.SUCCESS_BUSINESS_SIGN_UP;
        BusinessSignupResponseDto businessSignupResponseDto = businessService.signupForBiz(user, fileUrl, businessSignupRequestDto);
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), businessSignupResponseDto));
    }


}
