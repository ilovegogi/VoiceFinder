package com.ilovegogi.VoiceFinder.domain.reviewer.entity;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Type;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "activeAddress")
public class ActiveAddress {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activeAddress_id")
    private Long id;

    @OneToMany(mappedBy = "activeAddress")
    private List<ReviewerActiveAddress> reviewerActiveAddresses = new ArrayList<>();

    private String activeAddress;

    public ActiveAddress(String activeAddress) {
        this.activeAddress = activeAddress;
    }

    public static ActiveAddress from(String activeAddress) {
        return new ActiveAddress(activeAddress);
    }

}
