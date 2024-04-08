package com.ilovegogi.VoiceFinder.domain.campaign.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "campaignage")
public class CampaignAge {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaignage_id")
    private Long id;


}
