package com.ilovegogi.VoiceFinder.domain.apply.service;

import com.ilovegogi.VoiceFinder.domain.apply.dto.ApplyResponseDto;
import com.ilovegogi.VoiceFinder.domain.apply.entity.Apply;
import com.ilovegogi.VoiceFinder.domain.apply.repository.ApplyCampaignRepository;
import com.ilovegogi.VoiceFinder.domain.apply.repository.ApplyRepository;
import com.ilovegogi.VoiceFinder.domain.business.entity.Business;
import com.ilovegogi.VoiceFinder.domain.reviewer.entity.Reviewer;
import com.ilovegogi.VoiceFinder.domain.reviewer.repository.ReviewerRepository;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.domain.user.entity.UserRole;
import com.ilovegogi.VoiceFinder.global.exception.CustomException;
import com.ilovegogi.VoiceFinder.global.exception.ErrorCode;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplyService {

    private final ApplyRepository applyRepository;
    private final ApplyCampaignRepository applyCampaignRepository;
    private final ReviewerRepository reviewerRepository;

    @Transactional
    public ApplyResponseDto createApply(User user) {
        if (user.getUserRole().equals(UserRole.REVIEWER)) {
            Reviewer reviewer = validateReviewerByUser(user);
            Apply apply = Apply.builder()
                    .reviewer(reviewer)
                    .build();
            applyRepository.save(apply);
            return new ApplyResponseDto(apply.getId());
        }
        else throw new CustomException(ErrorCode.INVALID_APPLY);
    }

    private Reviewer validateReviewerByUser(User user) {
        return reviewerRepository.findByUser(user).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_REVIEWER));
    }
}
