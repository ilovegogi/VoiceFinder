package com.ilovegogi.VoiceFinder.domain.reviewer.service;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Job;
import com.ilovegogi.VoiceFinder.domain.campaign.entity.Type;
import com.ilovegogi.VoiceFinder.domain.campaign.repository.JobRepository;
import com.ilovegogi.VoiceFinder.domain.campaign.repository.TypeRepository;
import com.ilovegogi.VoiceFinder.domain.reviewer.dto.ReviewerSignupResponseDto;
import com.ilovegogi.VoiceFinder.domain.reviewer.dto.ReviewerSignupRequestDto;
import com.ilovegogi.VoiceFinder.domain.reviewer.entity.ActiveAddress;
import com.ilovegogi.VoiceFinder.domain.reviewer.entity.Reviewer;
import com.ilovegogi.VoiceFinder.domain.reviewer.entity.ReviewerActiveAddress;
import com.ilovegogi.VoiceFinder.domain.reviewer.entity.ReviewerType;
import com.ilovegogi.VoiceFinder.domain.reviewer.repository.ActiveAddressRepository;
import com.ilovegogi.VoiceFinder.domain.reviewer.repository.ReviewerActiveAddressRepository;
import com.ilovegogi.VoiceFinder.domain.reviewer.repository.ReviewerRepository;
import com.ilovegogi.VoiceFinder.domain.reviewer.repository.ReviewerTypeRepository;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.domain.user.repository.UserRepository;
import com.ilovegogi.VoiceFinder.global.exception.CustomException;
import com.ilovegogi.VoiceFinder.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReviewerService {

    private final ReviewerRepository reviewerRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final TypeRepository typeRepository;
    private final ReviewerTypeRepository reviewerTypeRepository;
    private final ActiveAddressRepository activeAddressRepository;
    private final ReviewerActiveAddressRepository reviewerActiveAddressRepository;


    @Transactional
    public ReviewerSignupResponseDto signupForReviewer(ReviewerSignupRequestDto reviewerSignupRequestDto) {
        User user = validateUserById(reviewerSignupRequestDto.getUserId());
        Reviewer reviewer = Reviewer.builder()
                .user(user)
                .job(validateJobExistAndCreate(reviewerSignupRequestDto.getJob()))
                .blogUrl(reviewerSignupRequestDto.getBlogUrl())
                .selfDescription(reviewerSignupRequestDto.getSelfDescription())
                .build();
        reviewerRepository.save(reviewer);
        List<Type> typeList = validateTypeExistAndCreate(reviewerSignupRequestDto.getType());
        for (Type type : typeList) {
            ReviewerType reviewerType = ReviewerType.builder()
                    .type(type)
                    .reviewer(reviewer)
                    .build();
            reviewerTypeRepository.save(reviewerType);
        }
        List<ActiveAddress> activeAddressList = validateActiveAddressExistAndCreate(reviewerSignupRequestDto.getActiveAddress());
        for (ActiveAddress activeAddress : activeAddressList) {
            ReviewerActiveAddress reviewerActiveAddress = ReviewerActiveAddress.builder()
                    .activeAddress(activeAddress)
                    .reviewer(reviewer)
                    .build();
            reviewerActiveAddressRepository.save(reviewerActiveAddress);
        }
        return new ReviewerSignupResponseDto(reviewer.getId());
    }

    private List<Type> validateTypeExistAndCreate(List<String> types) {
        List<Type> typeList = new ArrayList<>();
        for (String type : types) {
            Type getType = typeRepository.findByType(type)
                    .orElseGet(() -> createType(type));
            typeList.add(getType);
        }
        return typeList;
    }

    private List<ActiveAddress> validateActiveAddressExistAndCreate(List<String> activeAddresses) {
        List<ActiveAddress> activeAddressList = new ArrayList<>();
        for (String activeAddress : activeAddresses) {
            ActiveAddress getActiveAddress = activeAddressRepository.findByActiveAddress(activeAddress)
                    .orElseGet(() -> createActiveAddress(activeAddress));
            activeAddressList.add(getActiveAddress);
        }
        return activeAddressList;
    }


    private Job validateJobExistAndCreate(String job) {
        Job getJob = jobRepository.findByJob(job)
                .orElseGet(() -> createJob(job));
        return getJob;
    }

    private Job createJob(String job) {
        Job createJob = Job.from(job);
        jobRepository.save(createJob);
        return createJob;
    }

    private Type createType(String type) {
        Type createType = Type.from(type);
        typeRepository.save(createType);
        return createType;
    }

    private ActiveAddress createActiveAddress(String activeAddress) {
        ActiveAddress createActiveAddress = ActiveAddress.from(activeAddress);
        activeAddressRepository.save(createActiveAddress);
        return createActiveAddress;
    }

    private User validateUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BY_ID));
    }


}
