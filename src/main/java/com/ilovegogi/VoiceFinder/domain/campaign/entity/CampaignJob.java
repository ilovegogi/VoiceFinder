package com.ilovegogi.VoiceFinder.domain.campaign.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "campaignJob")
public class CampaignJob {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaignJob_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @Builder
    public CampaignJob(Campaign campaign, Job job) {
        this.campaign = campaign;
        this.job = job;
    }
}
