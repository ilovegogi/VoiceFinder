package com.ilovegogi.VoiceFinder.domain.campaign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignResponseDto<T> {
    private T data;
}