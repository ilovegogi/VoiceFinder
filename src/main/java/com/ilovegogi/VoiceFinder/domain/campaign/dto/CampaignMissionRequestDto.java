package com.ilovegogi.VoiceFinder.domain.campaign.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignMissionRequestDto {

    private String keyword;
    private String additionalKeyword;
    private int minTextNum;
    private int minImageNum;
    private Boolean isMap;
    private String etcComment;
    private String notandum;

}
