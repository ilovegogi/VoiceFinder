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
@Table(name = "job")
public class Job {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long id;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<CampaignJob> campaignJobs = new ArrayList<>();

    private String job;

    public Job(String job) {
        this.job = job;
    }

    public static Job from(String job) {
        return new Job(job);
    }
}
