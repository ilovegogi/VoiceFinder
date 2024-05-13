package com.ilovegogi.VoiceFinder.domain.apply.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "applyCampaign")
public class ApplyCampaign {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applyCampaign_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id")
    private Apply apply;

    @OneToMany(mappedBy = "campaign")
    private List<ApplyCampaign> applyCampaigns = new ArrayList<>();

}
