package com.ilovegogi.VoiceFinder.domain.reviewer.entity;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Campaign;
import com.ilovegogi.VoiceFinder.domain.campaign.entity.Type;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reviewerType")
public class ReviewerType {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewerType_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id")
    private Reviewer reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

    @Builder
    public ReviewerType(Reviewer reviewer, Type type) {
        this.reviewer = reviewer;
        this.type = type;
    }

}
