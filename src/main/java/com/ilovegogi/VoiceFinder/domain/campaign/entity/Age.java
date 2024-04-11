package com.ilovegogi.VoiceFinder.domain.campaign.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "age")
public class Age {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "age_id")
    private Long id;

    @OneToMany(mappedBy = "age", cascade = CascadeType.ALL)
    private List<CampaignAge> campaignAges = new ArrayList<>();

    private String age;

    public Age(String age) {
        this.age = age;
    }

    public static Age from(String age) {
        return new Age(age);
    }

}
