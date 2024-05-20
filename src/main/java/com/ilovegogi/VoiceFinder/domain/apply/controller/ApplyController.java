package com.ilovegogi.VoiceFinder.domain.apply.controller;

import com.ilovegogi.VoiceFinder.domain.apply.dto.ApplyRequestDto;
import com.ilovegogi.VoiceFinder.domain.apply.dto.ApplyResponseDto;
import com.ilovegogi.VoiceFinder.domain.apply.service.ApplyService;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.global.response.ApiResponse;
import com.ilovegogi.VoiceFinder.global.response.SuccessCode;
import com.ilovegogi.VoiceFinder.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/applies")
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponse> createApply(@AuthenticationPrincipal UserDetailsImpl userDetails, ApplyRequestDto applyRequestDto) {
        User user = userDetails.getUser();
        SuccessCode successCode = SuccessCode.SUCCESS_APPLY_CREATE;
        ApplyResponseDto applyResponseDto = applyService.createApply(user, applyRequestDto);
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), applyResponseDto));
    }


}
