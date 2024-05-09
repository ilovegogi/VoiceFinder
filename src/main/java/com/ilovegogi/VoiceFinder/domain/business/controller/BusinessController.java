package com.ilovegogi.VoiceFinder.domain.business.controller;

import com.ilovegogi.VoiceFinder.domain.business.dto.BusinessSignupRequestDto;
import com.ilovegogi.VoiceFinder.domain.business.dto.BusinessSignupResponseDto;
import com.ilovegogi.VoiceFinder.domain.business.service.BusinessService;
import com.ilovegogi.VoiceFinder.global.response.ApiResponse;
import com.ilovegogi.VoiceFinder.global.response.SuccessCode;
import com.ilovegogi.VoiceFinder.global.s3.FileUploadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/business")
public class BusinessController {

    private final BusinessService businessService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signupForBiz(@Valid @RequestBody BusinessSignupRequestDto businessSignupRequestDto) {
        BusinessSignupResponseDto businessSignupResponseDto = businessService.signupForBiz(businessSignupRequestDto);
        SuccessCode successCode = SuccessCode.SUCCESS_BUSINESS_SIGN_UP;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), businessSignupResponseDto));
    }


}
