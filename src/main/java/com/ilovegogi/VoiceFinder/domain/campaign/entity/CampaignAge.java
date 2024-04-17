package com.ilovegogi.VoiceFinder.domain.campaign.entity;

import com.ilovegogi.VoiceFinder.global.exception.CustomException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "campaignAge")
public class CampaignAge {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaignAge_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "age_id")
    private Age age;

    @Builder
    public CampaignAge(Campaign campaign, Age age) {
        this.campaign = campaign;
        this.age = age;
    }

}
