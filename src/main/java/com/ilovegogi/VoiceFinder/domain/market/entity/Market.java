package com.ilovegogi.VoiceFinder.domain.market.entity;

import com.ilovegogi.VoiceFinder.domain.business.entity.Business;
import com.ilovegogi.VoiceFinder.domain.campaign.entity.Campaign;
import com.ilovegogi.VoiceFinder.domain.campaign.entity.CampaignAge;
import com.ilovegogi.VoiceFinder.domain.reviewer.entity.Reviewer;
import com.ilovegogi.VoiceFinder.domain.user.entity.Address;
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
@Table(name = "market")
public class Market extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_id")
    private Long id;

    private String category;

    //@Embedded
    private String address;

    private String name;

    private String phoneNumber;

    @Comment("가게 한 줄 소개")
    private String description;

    @Comment("오시는 길")
    private String wayDescription;

    private Boolean isParking;

    private String parkingDescription;

    private String sns;

    private String detailDescription;



    //todo: 이미지 작업 + 위도 경도

    @ElementCollection
    @CollectionTable(name = "market_times", joinColumns = @JoinColumn(name = "market_id"))
    @Column(name = "time")
    private List<String> times = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "market_services", joinColumns = @JoinColumn(name = "market_id"))
    @Column(name = "service")
    private List<String> services = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "market_images", joinColumns = @JoinColumn(name = "market_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    @OneToMany(mappedBy = "market")
    private List<Campaign> campaigns = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    private Business business;

    @Builder
    public Market(Business business, List<String> times, List<String> services, List<String> imageUrls, String category, String address, String name, String phoneNumber, String description, String wayDescription, Boolean isParking, String parkingDescription, String sns, String detailDescription) {
        this.business = business;
        this.times = times;
        this.services = services;
        this.imageUrls = imageUrls;
        this.category = category;
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.wayDescription = wayDescription;
        this.isParking = isParking;
        this.parkingDescription = parkingDescription;
        this.sns = sns;
        this.detailDescription = detailDescription;
    }
}
