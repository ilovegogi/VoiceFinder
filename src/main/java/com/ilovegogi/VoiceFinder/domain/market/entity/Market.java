package com.ilovegogi.VoiceFinder.domain.market.entity;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Campaign;
import com.ilovegogi.VoiceFinder.domain.user.entity.Address;
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
@Table(name = "market")
public class Market {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_id")
    private Long id;

    private Category category;

    private Long ownerId;

    @Embedded
    private Address location;

    private String name;

    @Comment("가게 한 줄 소개")
    private String description;

    @Comment("오시는 길")
    private String wayDescription;

    //todo: 이미지 작업 + 위도 경도


    @OneToMany(mappedBy = "campaign",cascade = CascadeType.ALL)
    private List<Campaign> campaigns = new ArrayList<>();

    @Builder
    public Market(Category category, Long ownerId, Address location, String name, String description, String wayDescription) {
        this.category = category;
        this.ownerId = ownerId;
        this.location = location;
        this.name = name;
        this.description = description;
        this.wayDescription = wayDescription;
    }
}
