package com.ilovegogi.VoiceFinder.domain.campaign.entity;

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

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<CampaignType> campaignTypes = new ArrayList<>();

    private String type;

    public Type(String type) {
        this.type = type;
    }

    public static Type from(String type) {
        return new Type(type);
    }

}
