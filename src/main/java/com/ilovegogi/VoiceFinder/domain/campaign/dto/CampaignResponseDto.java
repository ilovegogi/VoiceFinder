package com.ilovegogi.VoiceFinder.domain.campaign.dto;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Campaign;
import com.ilovegogi.VoiceFinder.domain.campaign.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignResponseDto {

    private Long campaignId;
    private String marketName;
    private String campaignName;
    private LocalDateTime applyStartTime;
    private LocalDateTime applyEndTime;
    private LocalDateTime resultAnnouncementTime;
    private LocalDateTime registrationStartTime;
    private LocalDateTime registrationEndTime;
    private String provision;
    private String day;
    private String visitingTime;
    private String reservationDescription;
    private String keyword;
    private String additionalKeyword;
    private int minTextNum;
    private int minImageNum;
    private Boolean isMap;
    private String etcComment;
    private String notandum;
    private Gender gender;
    private List<String> ages;
    private List<String> jobs;
    private List<String> types;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CampaignResponseDto(Campaign campaign) {
        this.campaignId = campaign.getId();
        this.marketName = campaign.getCampaignName();
        this.applyStartTime = campaign.getApplyStartTime();
        this.applyEndTime = campaign.getApplyEndTime();
        this.resultAnnouncementTime = campaign.getResultAnnouncementTime();
        this.registrationStartTime = campaign.getRegistrationStartTime();
        this.registrationEndTime = campaign.getRegistrationEndTime();
        this.provision = campaign.getProvision();
        this.day = campaign.getDay();
        this.visitingTime = campaign.getVisitingTime();
        this.reservationDescription = campaign.getReservationDescription();
        this.keyword = campaign.getKeyword();
        this.additionalKeyword = campaign.getAdditionalKeyword();
        this.minTextNum = campaign.getMinTextNum();
        this.minImageNum = campaign.getMinImageNum();
        this.isMap = campaign.getIsMap();
        this.etcComment = campaign.getEtcComment();
        this.notandum = campaign.getNotandum();
        this.gender = campaign.getGender();
        this.ages = campaign.getCampaignAges().stream()
                .map(campaignAge -> campaignAge.getAge().getAge())
                .collect(Collectors.toList());
        this.jobs = campaign.getCampaignJobs().stream()
                .map(campaignJob -> campaignJob.getJob().getJob())
                .collect(Collectors.toList());
        this.types = campaign.getCampaignTypes().stream()
                .map(campaignType -> campaignType.getType().getType())
                .collect(Collectors.toList());
        this.createdAt = campaign.getCreatedAt();
        this.modifiedAt = campaign.getModifiedAt();
    }

}
