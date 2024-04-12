package com.ilovegogi.VoiceFinder.domain.campaign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignVisitInfoRequestDto {

    private String provision;
    private String day;
    private String visitingTime;
    private String reservationDescription;

}
