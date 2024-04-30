package com.ilovegogi.VoiceFinder.domain.campaign.dto;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Gender;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRequestDto {

    private Long marketId;
    private String title;
    private String keyword;
    private String description;
    private Gender gender;
    private List<String> age;
    private List<String> job;
    private List<String> imageUrls; // 이미지 URL 리스트 추가


}
