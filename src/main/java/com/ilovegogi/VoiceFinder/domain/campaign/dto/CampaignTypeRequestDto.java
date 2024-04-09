package com.ilovegogi.VoiceFinder.domain.campaign.dto;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignTypeRequestDto<T> {

    private Gender gender;

    private T age;
    private T job;
    private T type;

}
