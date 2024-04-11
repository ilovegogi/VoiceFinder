package com.ilovegogi.VoiceFinder.domain.campaign.service;

import com.ilovegogi.VoiceFinder.domain.campaign.dto.CampaignMissionRequestDto;
import com.ilovegogi.VoiceFinder.domain.campaign.dto.CampaignTimeVisitInfoRequestDto;
import com.ilovegogi.VoiceFinder.domain.campaign.dto.CampaignIdResponseDto;
import com.ilovegogi.VoiceFinder.domain.campaign.dto.CampaignTypeRequestDto;
import com.ilovegogi.VoiceFinder.domain.campaign.entity.*;
import com.ilovegogi.VoiceFinder.domain.campaign.repository.*;
import com.ilovegogi.VoiceFinder.domain.market.entity.Market;
import com.ilovegogi.VoiceFinder.domain.market.repository.MarketRepository;
import com.ilovegogi.VoiceFinder.global.exception.CustomException;
import com.ilovegogi.VoiceFinder.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final MarketRepository marketRepository;
    private final JobRepository jobRepository;
    private final TypeRepository typeRepository;
    private final AgeRepository ageRepository;
    private final CampaignAgeRepository campaignAgeRepository;
    private final CampaignJobRepository campaignJobRepository;
    private final CampaignTypeRepository campaignTypeRepository;

    @Transactional
    public CampaignIdResponseDto registrationCampaignTimeVisitInfo(Long marketId, CampaignTimeVisitInfoRequestDto campaignTimeVisitInfoRequestDto) {
        Market market = validateMarketById(marketId);
        Campaign campaign = Campaign.builder()
                .applyStartTime(campaignTimeVisitInfoRequestDto.getApplyStartTime())
                .applyEndTime(campaignTimeVisitInfoRequestDto.getApplyEndTime())
                .resultAnnouncementTime(campaignTimeVisitInfoRequestDto.getResultAnnouncementTime())
                .registrationStartTime(campaignTimeVisitInfoRequestDto.getRegistrationStartTime())
                .registrationEndTime(campaignTimeVisitInfoRequestDto.getRegistrationEndTime())
                .provision(campaignTimeVisitInfoRequestDto.getProvision())
                .visitingTime(campaignTimeVisitInfoRequestDto.getVisitingTime())
                .day(campaignTimeVisitInfoRequestDto.getDay())
                .reservationDescription(campaignTimeVisitInfoRequestDto.getReservationDescription())
                .market(market)
                .build();
        campaignRepository.save(campaign);
        return new CampaignIdResponseDto(campaign.getId());
    }

    private Market validateMarketById(Long marketId) {
        return marketRepository.findById(marketId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MARKET));
    }

    private Campaign validateCampaignById(Long campaignId) {
        return campaignRepository.findById(campaignId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CAMPAIGN));
    }

    @Transactional
    public CampaignIdResponseDto registrationCampaignMission(Long marketId, Long campaignId, CampaignMissionRequestDto campaignMissionRequestDto) {
        validateMarketById(marketId);
        Campaign campaign = validateCampaignById(campaignId);
        campaign.registrationCampaignMission(campaignMissionRequestDto);
        campaignRepository.save(campaign);
        return new CampaignIdResponseDto(campaign.getId());
    }

    //todo : 정적팩토리 메서드로 객체 만들기

    @Transactional
    public CampaignIdResponseDto registrationCampaignType(Long marketId, Long campaignId, CampaignTypeRequestDto campaignTypeRequestDto) {
        validateMarketById(marketId);
        Campaign campaign = validateCampaignById(campaignId);
        campaign.registrationCampaignGender(campaignTypeRequestDto.getGender());
        List<Age> ageList = validateAgeExistAndCreate(campaignTypeRequestDto.getAge());
        for (Age age : ageList) {
            CampaignAge campaignAge = CampaignAge.builder()
                    .age(age)
                    .campaign(campaign)
                    .build();
            campaignAgeRepository.save(campaignAge);
        }
        List<Job> jobList = validateJobExistAndCreate(campaignTypeRequestDto.getJob());
        for (Job job : jobList) {
            CampaignJob campaignJob = CampaignJob.builder()
                    .job(job)
                    .campaign(campaign)
                    .build();
            campaignJobRepository.save(campaignJob);
        }
        List<Type> typeList = validateTypeExistAndCreate(campaignTypeRequestDto.getType());
        for (Type type : typeList) {
            CampaignType campaignType = CampaignType.builder()
                    .type(type)
                    .campaign(campaign)
                    .build();
            campaignTypeRepository.save(campaignType);
        }
        return new CampaignIdResponseDto((campaign.getId()));
    }

    private List<Age> validateAgeExistAndCreate(List<String> ages) {
        List<Age> ageList = new ArrayList<>(); //Age 객체 리스트
        for (String age : ages) {
            Age getAge = ageRepository.findByAge(age)
                    .orElseGet(() -> createAge(age));
            ageList.add(getAge); //age 객체 추가
        }
        return ageList;
    }

    private List<Job> validateJobExistAndCreate(List<String> jobs) {
        List<Job> jobList = new ArrayList<>(); //Age 객체 리스트
        for (String job : jobs) {
            Job getJob = jobRepository.findByJob(job)
                    .orElseGet(() -> createJob(job));
            jobList.add(getJob); //age 객체 추가
        }
        return jobList;
    }

    private List<Type> validateTypeExistAndCreate(List<String> types) {
        List<Type> typeList = new ArrayList<>(); //Age 객체 리스트
        for (String type : types) {
            Type getType = typeRepository.findByType(type)
                    .orElseGet(() -> createType(type));
            typeList.add(getType); //age 객체 추가
        }
        return typeList;
    }

    private Age createAge(String age) {
        Age createAge = Age.from(age);
        ageRepository.save(createAge);
        return createAge;
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

}
