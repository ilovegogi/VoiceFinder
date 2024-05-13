package com.ilovegogi.VoiceFinder.domain.reviewer.controller;

import com.ilovegogi.VoiceFinder.domain.reviewer.dto.ReviewerSignupResponseDto;
import com.ilovegogi.VoiceFinder.domain.reviewer.dto.ReviewerSignupRequestDto;
import com.ilovegogi.VoiceFinder.domain.reviewer.service.ReviewerService;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.global.response.ApiResponse;
import com.ilovegogi.VoiceFinder.global.response.SuccessCode;
import com.ilovegogi.VoiceFinder.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviewer")
public class ReviewerController {

    private final ReviewerService reviewerService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signupForReviewer(@AuthenticationPrincipal UserDetailsImpl userDetails, @Valid @RequestBody ReviewerSignupRequestDto reviewerSignupRequestDto) {
        User user = userDetails.getUser();
        ReviewerSignupResponseDto reviewerSignupResponseDto = reviewerService.signupForReviewer(user, reviewerSignupRequestDto);
        SuccessCode successCode = SuccessCode.SUCCESS_REVIEWER_SIGN_UP;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), reviewerSignupResponseDto));
    }


}
