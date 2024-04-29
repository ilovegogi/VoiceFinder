package com.ilovegogi.VoiceFinder.domain.campaign.service;

import com.ilovegogi.VoiceFinder.domain.campaign.dto.*;
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
import java.util.stream.Collectors;

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
                .campaignName(campaignTimeVisitInfoRequestDto.getCampaignName())
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

    private void validateCampaignExist(Long campaignId) {
        campaignRepository.findById(campaignId)
                .ifPresent(exist -> {
                    throw new CustomException(ErrorCode.EXISTED_MARKET);
                });
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
        campaignRepository.save(campaign);
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
        List<Age> ageList = new ArrayList<>();
        for (String age : ages) {
            Age getAge = ageRepository.findByAge(age)
                    .orElseGet(() -> createAge(age));
            ageList.add(getAge);
        }
        return ageList;
    }

    private List<Job> validateJobExistAndCreate(List<String> jobs) {
        List<Job> jobList = new ArrayList<>();
        for (String job : jobs) {
            Job getJob = jobRepository.findByJob(job)
                    .orElseGet(() -> createJob(job));
            jobList.add(getJob);
        }
        return jobList;
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


    public CampaignResponseDto getCampaignNameList() {
        List<Campaign> campaigns = campaignRepository.findAll();
        List<CampaignNameListResponseDto> list = campaigns.stream()
                .map(c -> new CampaignNameListResponseDto(c.getId(), c.getCampaignName()))
                .collect(Collectors.toList());
        return new CampaignResponseDto(list);
    }

    public CampaignResponseDto getCampaignList() {
        List<Campaign> campaigns = campaignRepository.findAllWithAges();
        List<CampaignListResponseDto> list = campaigns.stream()
                .map(c -> {
                    List<String> ages = c.getCampaignAges().stream()
                            .map(campaignAge -> campaignAge.getAge().getAge())
                            .collect(Collectors.toList());
                    List<String> jobs = c.getCampaignJobs().stream()
                            .map(campaignJob -> campaignJob.getJob().getJob())
                            .collect(Collectors.toList());
                    List<String> types = c.getCampaignTypes().stream()
                            .map(campaignType -> campaignType.getType().getType())
                            .collect(Collectors.toList());
                    return new CampaignListResponseDto(c.getId(), c.getMarket().getName(), c.getCampaignName(), c.getApplyStartTime(), c.getApplyEndTime(), c.getResultAnnouncementTime(), c.getRegistrationStartTime(), c.getRegistrationEndTime(), c.getProvision(), c.getDay(), c.getVisitingTime(), c.getReservationDescription(), c.getKeyword(), c.getAdditionalKeyword(), c.getMinTextNum(), c.getMinImageNum(), c.getIsMap(), c.getEtcComment(), c.getNotandum(), c.getGender(), ages, jobs, types);
                })
                .collect(Collectors.toList());
        return new CampaignResponseDto(list);
    }

    public CampaignListResponseDto getCampaignById(Long id) {
        Campaign c = validateCampaignById(id);
        List<String> ages = c.getCampaignAges().stream()
                .map(campaignAge -> campaignAge.getAge().getAge())
                .collect(Collectors.toList());
        List<String> jobs = c.getCampaignJobs().stream()
                .map(campaignJob -> campaignJob.getJob().getJob())
                .collect(Collectors.toList());
        List<String> types = c.getCampaignTypes().stream()
                .map(campaignType -> campaignType.getType().getType())
                .collect(Collectors.toList());
        return new CampaignListResponseDto(c.getId(), c.getMarket().getName(), c.getCampaignName(), c.getApplyStartTime(), c.getApplyEndTime(), c.getResultAnnouncementTime(), c.getRegistrationStartTime(), c.getRegistrationEndTime(), c.getProvision(), c.getDay(), c.getVisitingTime(), c.getReservationDescription(), c.getKeyword(), c.getAdditionalKeyword(), c.getMinTextNum(), c.getMinImageNum(), c.getIsMap(), c.getEtcComment(), c.getNotandum(), c.getGender(), ages, jobs, types);

    }


}
