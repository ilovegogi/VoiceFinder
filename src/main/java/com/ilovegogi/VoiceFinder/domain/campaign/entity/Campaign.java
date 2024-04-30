package com.ilovegogi.VoiceFinder.domain.campaign.entity;

import com.ilovegogi.VoiceFinder.domain.campaign.dto.CampaignMissionRequestDto;
import com.ilovegogi.VoiceFinder.domain.campaign.dto.CampaignRequestDto;
import com.ilovegogi.VoiceFinder.domain.campaign.dto.CampaignResponseDto;
import com.ilovegogi.VoiceFinder.domain.market.entity.Market;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "campaign")
public class Campaign extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "market_id")
    private Market market;

    private String title;
    private String keyword;
    private String description;
    private Gender gender;
    private List<String> age;
    private List<String> job;
    @ElementCollection
    @CollectionTable(name = "campaign_images", joinColumns = @JoinColumn(name = "campaign_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    public Campaign(Market market, CampaignRequestDto requestDto) {
        this.market = market;
        this.title = requestDto.getTitle();
        this.keyword = requestDto.getKeyword();
        this.description = requestDto.getDescription();
        this.gender = requestDto.getGender();
        this.age = requestDto.getAge();
        this.job = requestDto.getJob();
        this.imageUrls = requestDto.getImageUrls();
    }

//    @Builder
//    public Campaign(LocalDateTime applyStartTime, LocalDateTime applyEndTime, LocalDateTime resultAnnouncementTime, LocalDateTime registrationStartTime, LocalDateTime registrationEndTime, String provision, String day, String visitingTime, String reservationDescription, Market market) {
//        this.applyStartTime = applyStartTime;
//        this.applyEndTime = applyEndTime;
//        this.resultAnnouncementTime = resultAnnouncementTime;
//        this.registrationStartTime = registrationStartTime;
//        this.registrationEndTime = registrationEndTime;
//        this.provision = provision;
//        this.day = day;
//        this.visitingTime = visitingTime;
//        this.reservationDescription = reservationDescription;
//        this.market = market;
//    }

//    public void registrationCampaignMission(CampaignMissionRequestDto campaignMissionRequestDto) {
//        keyword = campaignMissionRequestDto.getKeyword();
//        additionalKeyword = campaignMissionRequestDto.getAdditionalKeyword();
//        minTextNum = campaignMissionRequestDto.getMinTextNum();
//        minImageNum = campaignMissionRequestDto.getMinImageNum();
//        isMap = campaignMissionRequestDto.getIsMap();
//        etcComment = campaignMissionRequestDto.getEtcComment();
//        notandum = campaignMissionRequestDto.getNotandum();
//    }
//
//    public void registrationCampaignGender(Gender getGender) {
//        gender = getGender;
//    }

}
