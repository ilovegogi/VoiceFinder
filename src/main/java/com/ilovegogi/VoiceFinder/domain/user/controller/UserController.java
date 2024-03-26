package com.ilovegogi.VoiceFinder.domain.user.controller;

import com.ilovegogi.VoiceFinder.domain.user.dto.SignupRequestDto;
import com.ilovegogi.VoiceFinder.domain.user.dto.UpdateProfileRequestDto;
import com.ilovegogi.VoiceFinder.domain.user.dto.UserProfileDto;
import com.ilovegogi.VoiceFinder.domain.user.service.UserServiceImpl;
import com.ilovegogi.VoiceFinder.global.response.ApiResponse;
import com.ilovegogi.VoiceFinder.global.response.SuccessCode;
import com.ilovegogi.VoiceFinder.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        UserProfileDto dto = userService.signup(requestDto);
        SuccessCode successCode = SuccessCode.SUCCESS_USER_SIGN_UP;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), dto));
    }

    // 프로필 수정
    @PutMapping("/profile")
    @ResponseBody
    public ResponseEntity<ApiResponse> updateProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UpdateProfileRequestDto requestDto) {
        UserProfileDto dto = userService.updateUserProfile(userDetails.getUser(), requestDto);
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