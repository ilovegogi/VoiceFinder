package com.ilovegogi.VoiceFinder.domain.campaign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignTimeVisitInfoRequestDto {

    private LocalDateTime applyStartTime;
    private LocalDateTime applyEndTime;
    private LocalDateTime resultAnnouncementTime;
    private LocalDateTime registrationStartTime;
    private LocalDateTime registrationEndTime;

    private String provision;
    private String day;
    private String visitingTime;
    private String reservationDescription;

}
