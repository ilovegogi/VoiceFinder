package com.ilovegogi.VoiceFinder.domain.user.controller;

import com.ilovegogi.VoiceFinder.domain.user.dto.SignupRequestDto;
import com.ilovegogi.VoiceFinder.domain.user.dto.UpdateProfileRequestDto;
import com.ilovegogi.VoiceFinder.domain.user.dto.UserProfileDto;
import com.ilovegogi.VoiceFinder.domain.user.service.UserServiceImpl;
import com.ilovegogi.VoiceFinder.global.response.ApiResponse;
import com.ilovegogi.VoiceFinder.global.response.SuccessCode;
import com.ilovegogi.VoiceFinder.global.s3.FileUploadService;
import com.ilovegogi.VoiceFinder.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Slf4j
@Controller
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;
    private final FileUploadService fileUploadService;

    public UserController(UserServiceImpl userService, FileUploadService fileUploadService) {
        this.userService = userService;
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        UserProfileDto dto = userService.signup(requestDto);
        SuccessCode successCode = SuccessCode.SUCCESS_USER_SIGN_UP;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), dto));
    }

    // 프로필 수정
    @PutMapping(value = "/profile")
    @ResponseBody
    public ResponseEntity<ApiResponse> updateProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @ModelAttribute UpdateProfileRequestDto requestDto) throws IOException {
        String imageUrl = null;
        if (requestDto.getImageUrl() != null && !requestDto.getImageUrl().isEmpty()) {
            imageUrl = fileUploadService.uploadFile(requestDto.getImageUrl());
        }

        UserProfileDto dto = userService.updateUserProfile(userDetails.getUser(), requestDto, imageUrl);
        SuccessCode successCode = SuccessCode.SUCCESS_UPDATE_USER_INFO;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), dto));
    }

    // 프로필 조회
    @GetMapping("/profile")
    @ResponseBody
    public UserProfileDto getUserProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new UserProfileDto(userDetails.getUser());
    }

}