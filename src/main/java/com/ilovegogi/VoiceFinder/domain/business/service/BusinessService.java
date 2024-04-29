package com.ilovegogi.VoiceFinder.domain.business.service;

import com.ilovegogi.VoiceFinder.domain.business.dto.BusinessSignupRequestDto;
import com.ilovegogi.VoiceFinder.domain.business.dto.BusinessSignupResponseDto;
import com.ilovegogi.VoiceFinder.domain.business.entity.Business;
import com.ilovegogi.VoiceFinder.domain.business.repository.BusinessRepository;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.domain.user.repository.UserRepository;
import com.ilovegogi.VoiceFinder.global.exception.CustomException;
import com.ilovegogi.VoiceFinder.global.exception.ErrorCode;
import com.ilovegogi.VoiceFinder.global.s3.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusinessService {

    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;

    @Transactional
    public BusinessSignupResponseDto signupForBiz(BusinessSignupRequestDto businessSignupRequestDto) {
        User user = validateUserById(businessSignupRequestDto.getUserId());
        String bizFileUrl = null;
        if (businessSignupRequestDto.getBizFileUrl() != null && !businessSignupRequestDto.getBizFileUrl().isEmpty()) {
            try {
                bizFileUrl = fileUploadService.uploadFile(businessSignupRequestDto.getBizFileUrl());
            } catch (IOException e) {
                throw new CustomException(ErrorCode.FILE_UPLOAD_EXCEPTION);
            }
        }
        Business business = Business.builder()
                .user(user)
                .bizName(businessSignupRequestDto.getBizName())
                .bossName(businessSignupRequestDto.getBossName())
                .bizNum(businessSignupRequestDto.getBizNum())
                .bizFileUrl(bizFileUrl)
                .bizClause1(businessSignupRequestDto.getBizClause1())
                .bizClause2(businessSignupRequestDto.getBizClause2())
                .build();
        businessRepository.save(business);
        return new BusinessSignupResponseDto(business.getId());
    }

    private User validateUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BY_ID));
    }

}
