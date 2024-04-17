package com.ilovegogi.VoiceFinder.domain.reviewer.entity;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Job;
import com.ilovegogi.VoiceFinder.domain.campaign.entity.Type;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reviewer")
public class Reviewer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewer_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    private List<ReviewerActiveAddress> reviewerActiveAddresses = new ArrayList<>();

    @OneToMany(mappedBy = "reviewer")
    private List<Type> types = new ArrayList<>();

    @Comment("블로그 주소")
    private String blogUrl;

    @Comment("자기 소개")
    private String selfDescription;



}
