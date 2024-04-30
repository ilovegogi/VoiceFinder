package com.ilovegogi.VoiceFinder.domain.campaign.dto;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Campaign;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignResponseDto {

    private Long id;

    private String marketName;
    private String title;
    private String keyword;
    private String description;
    private String address;
    private List<String> imageUrls;

    public CampaignResponseDto(Campaign campaign) {
        this.id = campaign.getId();
        this.marketName = campaign.getMarket().getName();
        this.address = campaign.getMarket().getAddress();
        this.title = campaign.getTitle();
        this.keyword = campaign.getKeyword();
        this.description = campaign.getDescription();
        this.imageUrls = campaign.getImageUrls();
    }
}
