package com.ilovegogi.VoiceFinder.domain.campaign.dto;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignRequestDto {

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
    private List<String> imageUrls;
}
