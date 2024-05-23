package com.ilovegogi.VoiceFinder.domain.apply.dto;


import com.ilovegogi.VoiceFinder.domain.apply.entity.Apply;
import com.ilovegogi.VoiceFinder.domain.campaign.entity.Campaign;
import com.ilovegogi.VoiceFinder.domain.reviewer.entity.Reviewer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplyListResponseDto {

    private Long applyId;
    private Campaign campaign;
    private Reviewer reviewer;

    public ApplyListResponseDto(Apply apply) {
        this.applyId = applyId;
        this.campaign = campaign;
        this.reviewer = reviewer;
    }
}
