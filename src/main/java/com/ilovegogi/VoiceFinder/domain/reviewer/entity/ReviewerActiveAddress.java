package com.ilovegogi.VoiceFinder.domain.reviewer.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reviewerActiveAddress")
public class ReviewerActiveAddress {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewerActiveAddress")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id")
    private Reviewer reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activeAddress_id")
    private ActiveAddress activeAddress;

}
