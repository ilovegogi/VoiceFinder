package com.ilovegogi.VoiceFinder.domain.user.service;


import com.ilovegogi.VoiceFinder.domain.user.dto.SignupRequestDto;
import com.ilovegogi.VoiceFinder.domain.user.dto.UserProfileDto;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;


public interface UserService {

    // 회원 가입
    UserProfileDto signup(SignupRequestDto requestDto);


    // 프로필 수정
    UserProfileDto updateUserProfile(User user, SignupRequestDto.UpdateProfileRequestDto requestDto);
}