package com.ilovegogi.VoiceFinder.domain.campaign.dto;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignTypeRequestDto {

    private Long marketId;

    private Gender gender;
    private List<String> age;
    private List<String> job;
    private List<String> type;

}
