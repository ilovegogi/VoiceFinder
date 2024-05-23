package com.ilovegogi.VoiceFinder.domain.apply.service;

import com.ilovegogi.VoiceFinder.domain.apply.dto.ApplyListResponseDto;
import com.ilovegogi.VoiceFinder.domain.apply.dto.ApplyRequestDto;
import com.ilovegogi.VoiceFinder.domain.apply.dto.ApplyResponseDto;
import com.ilovegogi.VoiceFinder.domain.apply.entity.Apply;
import com.ilovegogi.VoiceFinder.domain.apply.entity.QApply;
import com.ilovegogi.VoiceFinder.domain.apply.repository.ApplyRepository;
import com.ilovegogi.VoiceFinder.domain.campaign.dto.CampaignResponseDto;
import com.ilovegogi.VoiceFinder.domain.campaign.entity.Campaign;
import com.ilovegogi.VoiceFinder.domain.campaign.repository.CampaignRepository;
import com.ilovegogi.VoiceFinder.domain.market.entity.QMarket;
import com.ilovegogi.VoiceFinder.domain.reviewer.entity.Reviewer;
import com.ilovegogi.VoiceFinder.domain.reviewer.repository.ReviewerRepository;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.domain.user.entity.UserRole;
import com.ilovegogi.VoiceFinder.global.exception.CustomException;
import com.ilovegogi.VoiceFinder.global.exception.ErrorCode;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplyService {

    private final ApplyRepository applyRepository;
    private final ReviewerRepository reviewerRepository;
    private final CampaignRepository campaignRepository;

    @Transactional
    public ApplyResponseDto createApply(User user, ApplyRequestDto applyRequestDto) {
        if (user.getUserRole().equals(UserRole.REVIEWER)) {
            Reviewer reviewer = validateReviewerByUser(user);
            Campaign campaign = validateCampaignById(applyRequestDto.getCampaignId());
            Apply apply = Apply.builder()
                    .reviewer(reviewer)
                    .campaign(campaign)
                    .build();
            applyRepository.save(apply);
            return new ApplyResponseDto(apply.getId());
        }
        else throw new CustomException(ErrorCode.INVALID_APPLY);
    }

    private Reviewer validateReviewerByUser(User user) {
        return reviewerRepository.findByUser(user).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_REVIEWER));
    }

    private Campaign validateCampaignById(Long campaignId) {
        return campaignRepository.findById(campaignId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CAMPAIGN));
    }

    public Page<ApplyListResponseDto> getApplies(int page, int size, String sortBy, boolean isAsc, String keyword) {
        Sort sort = Sort.by(isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        if (keyword != null && !keyword.isEmpty()) {
            QApply qApply = QApply.apply;
            BooleanBuilder builder = new BooleanBuilder();
            builder.and(qApply.reviewer.selfDescription.containsIgnoreCase(keyword)
                    .or(qApply.reviewer.job.job.containsIgnoreCase(keyword)));
            return applyRepository.findAll(builder, pageable).map(ApplyListResponseDto::new);
        } else {
            return applyRepository.findAll(pageable).map(ApplyListResponseDto::new);
        }
    }

}
