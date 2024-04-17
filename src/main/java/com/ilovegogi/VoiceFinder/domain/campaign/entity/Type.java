package com.ilovegogi.VoiceFinder.domain.campaign.entity;

import com.ilovegogi.VoiceFinder.domain.reviewer.entity.Reviewer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "type")
public class Type {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long id;

    @OneToMany(mappedBy = "type")
    private List<CampaignType> campaignTypes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id")
    private Reviewer reviewer;

    private String type;

    public Type(String type) {
        this.type = type;
    }

    public static Type from(String type) {
        return new Type(type);
    }

}
