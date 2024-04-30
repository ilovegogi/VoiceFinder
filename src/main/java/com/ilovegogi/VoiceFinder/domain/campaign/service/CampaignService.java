package com.ilovegogi.VoiceFinder.domain.campaign.service;

import com.ilovegogi.VoiceFinder.domain.campaign.dto.*;
import com.ilovegogi.VoiceFinder.domain.campaign.entity.*;
import com.ilovegogi.VoiceFinder.domain.campaign.repository.*;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketRegistrationRequestDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketResponseDto;
import com.ilovegogi.VoiceFinder.domain.market.entity.Market;
import com.ilovegogi.VoiceFinder.domain.market.entity.QMarket;
import com.ilovegogi.VoiceFinder.domain.market.repository.MarketRepository;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final MarketRepository marketRepository;
    private final JobRepository jobRepository;
    private final TypeRepository typeRepository;
    private final AgeRepository ageRepository;
    private final CampaignAgeRepository campaignAgeRepository;
    private final CampaignJobRepository campaignJobRepository;
    private final CampaignTypeRepository campaignTypeRepository;

    public CampaignResponseDto createCampaign(User user, CampaignRequestDto requestDto) {
        Market market = findMarket(requestDto.getMarketId());

        Campaign campaign = new Campaign(market, requestDto);
        // DB 저장
        Campaign saveCampaign = campaignRepository.save(campaign);

        return new CampaignResponseDto(saveCampaign);

//        System.out.println("market = " + market);
//        System.out.println("market.getUser() = " + market.getUser());
//        System.out.println("user = " + user);

//        if (market.getUser().equals(user)) {
//            // RequestDto -> Entity
//            Campaign campaign = new Campaign(market, requestDto);
//            // DB 저장
//            Campaign saveCampaign = campaignRepository.save(campaign);
//
//            return new CampaignResponseDto(saveCampaign);
//        } else {
//            throw new CustomException(ErrorCode.INVALID_ADMIN_TOKEN);
//        }

    }

    public Page<CampaignResponseDto> getCampaigns(int page, int size, String sortBy, boolean isAsc, String keyword) {
        Sort sort = Sort.by(isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
//        System.out.println("keyword = " + keyword);

        if (keyword != null && !keyword.isEmpty()) {
            QMarket qMarket = QMarket.market;
            BooleanBuilder builder = new BooleanBuilder();
            builder.and(qMarket.name.containsIgnoreCase(keyword)
                    .or(qMarket.description.containsIgnoreCase(keyword)));
            return campaignRepository.findAll(builder, pageable).map(CampaignResponseDto::new);
        } else {
            return campaignRepository.findAll(pageable).map(CampaignResponseDto::new);
        }
    }

    public CampaignResponseDto getCampaignDto(Long id) {
        Campaign campaign = findCampaign(id);
        CampaignResponseDto campaignResponseDto = new CampaignResponseDto(campaign);
        return campaignResponseDto;
    }

    private Campaign findCampaign(Long id) {
        return campaignRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_CAMPAIGN)
        );
    }

    private Market findMarket(Long id) {
        return marketRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_MARKET)
        );
    }

    public Page<CampaignResponseDto> getRecommendedCampaigns() {
        Pageable pageable = PageRequest.of(0, 3);
        return campaignRepository.findAll(pageable).map(CampaignResponseDto::new);
    }

//    @Transactional
//    public CampaignIdResponseDto registrationCampaignTimeVisitInfo(Long marketId, CampaignTimeVisitInfoRequestDto campaignTimeVisitInfoRequestDto) {
//        Market market = validateMarketById(marketId);
//        Campaign campaign = Campaign.builder()
//                .applyStartTime(campaignTimeVisitInfoRequestDto.getApplyStartTime())
//                .applyEndTime(campaignTimeVisitInfoRequestDto.getApplyEndTime())
//                .resultAnnouncementTime(campaignTimeVisitInfoRequestDto.getResultAnnouncementTime())
//                .registrationStartTime(campaignTimeVisitInfoRequestDto.getRegistrationStartTime())
//                .registrationEndTime(campaignTimeVisitInfoRequestDto.getRegistrationEndTime())
//                .provision(campaignTimeVisitInfoRequestDto.getProvision())
//                .visitingTime(campaignTimeVisitInfoRequestDto.getVisitingTime())
//                .day(campaignTimeVisitInfoRequestDto.getDay())
//                .reservationDescription(campaignTimeVisitInfoRequestDto.getReservationDescription())
//                .market(market)
//                .build();
//        campaignRepository.save(campaign);
//        return new CampaignIdResponseDto(campaign.getId());
//    }
//
//    private Market validateMarketById(Long marketId) {
//        return marketRepository.findById(marketId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MARKET));
//    }
//
//    private Campaign validateCampaignById(Long campaignId) {
//        return campaignRepository.findById(campaignId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CAMPAIGN));
//    }
//
//    @Transactional
//    public CampaignIdResponseDto registrationCampaignMission(Long marketId, Long campaignId, CampaignMissionRequestDto campaignMissionRequestDto) {
//        validateMarketById(marketId);
//        Campaign campaign = validateCampaignById(campaignId);
//        campaign.registrationCampaignMission(campaignMissionRequestDto);
//        campaignRepository.save(campaign);
//        return new CampaignIdResponseDto(campaign.getId());
//    }
//
//    //todo : 정적팩토리 메서드로 객체 만들기
//
//    @Transactional
//    public CampaignIdResponseDto registrationCampaignType(Long marketId, Long campaignId, CampaignTypeRequestDto campaignTypeRequestDto) {
//        validateMarketById(marketId);
//        Campaign campaign = validateCampaignById(campaignId);
//        campaign.registrationCampaignGender(campaignTypeRequestDto.getGender());
//        campaignRepository.save(campaign);
//        List<Age> ageList = validateAgeExistAndCreate(campaignTypeRequestDto.getAge());
//        for (Age age : ageList) {
//            CampaignAge campaignAge = CampaignAge.builder()
//                    .age(age)
//                    .campaign(campaign)
//                    .build();
//            campaignAgeRepository.save(campaignAge);
//        }
//        List<Job> jobList = validateJobExistAndCreate(campaignTypeRequestDto.getJob());
//        for (Job job : jobList) {
//            CampaignJob campaignJob = CampaignJob.builder()
//                    .job(job)
//                    .campaign(campaign)
//                    .build();
//            campaignJobRepository.save(campaignJob);
//        }
//        List<Type> typeList = validateTypeExistAndCreate(campaignTypeRequestDto.getType());
//        for (Type type : typeList) {
//            CampaignType campaignType = CampaignType.builder()
//                    .type(type)
//                    .campaign(campaign)
//                    .build();
//            campaignTypeRepository.save(campaignType);
//        }
//        return new CampaignIdResponseDto((campaign.getId()));
//    }
//
//    private List<Age> validateAgeExistAndCreate(List<String> ages) {
//        List<Age> ageList = new ArrayList<>();
//        for (String age : ages) {
//            Age getAge = ageRepository.findByAge(age)
//                    .orElseGet(() -> createAge(age));
//            ageList.add(getAge);
//        }
//        return ageList;
//    }
//
//    private List<Job> validateJobExistAndCreate(List<String> jobs) {
//        List<Job> jobList = new ArrayList<>();
//        for (String job : jobs) {
//            Job getJob = jobRepository.findByJob(job)
//                    .orElseGet(() -> createJob(job));
//            jobList.add(getJob);
//        }
//        return jobList;
//    }
//
//    private List<Type> validateTypeExistAndCreate(List<String> types) {
//        List<Type> typeList = new ArrayList<>();
//        for (String type : types) {
//            Type getType = typeRepository.findByType(type)
//                    .orElseGet(() -> createType(type));
//            typeList.add(getType);
//        }
//        return typeList;
//    }
//
//    private Age createAge(String age) {
//        Age createAge = Age.from(age);
//        ageRepository.save(createAge);
//        return createAge;
//    }
//
//    private Job createJob(String job) {
//        Job createJob = Job.from(job);
//        jobRepository.save(createJob);
//        return createJob;
//    }
//
//    private Type createType(String type) {
//        Type createType = Type.from(type);
//        typeRepository.save(createType);
//        return createType;
//    }

}
