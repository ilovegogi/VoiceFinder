package com.ilovegogi.VoiceFinder.domain.reviewer.entity;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Job;
import com.ilovegogi.VoiceFinder.domain.campaign.entity.Type;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reviewer")
public class Reviewer extends Timestamped {

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

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    private List<ReviewerType> reviewerTypes = new ArrayList<>();

    @Comment("블로그 주소")
    private String blogUrl;

    @Comment("자기 소개")
    private String selfDescription;

    @Comment("서비스 이용 약관")
    private Boolean reviewerClause1;

    @Comment("개인정보 수집 동의")
    private Boolean reviewerClause2;

    @Builder
    public Reviewer(User user, Job job, String blogUrl, String selfDescription, Boolean reviewerClause1, Boolean reviewerClause2) {
        this.user = user;
        this.job = job;
        this.blogUrl = blogUrl;
        this.selfDescription = selfDescription;
        this.reviewerClause1 = reviewerClause1;
        this.reviewerClause2 = reviewerClause2;
    }



}
