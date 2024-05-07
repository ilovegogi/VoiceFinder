package com.ilovegogi.VoiceFinder.domain.campaign.entity;

import com.ilovegogi.VoiceFinder.domain.campaign.dto.CampaignMissionRequestDto;
import com.ilovegogi.VoiceFinder.domain.market.entity.Market;
import com.ilovegogi.VoiceFinder.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "campaign")
public class Campaign extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private Long id;

    @Comment("캠페인 이름")
    private String campaignName;

    @Comment("신청 시작 시간")
    private LocalDateTime applyStartTime;

    private LocalDateTime applyEndTime;

    @Comment("결과 발표 시간")
    private LocalDateTime resultAnnouncementTime;

    @Comment("콘텐츠 등록 시작 시간")
    private LocalDateTime registrationStartTime;

    private LocalDateTime registrationEndTime;

    @Comment("제공 내역")
    private String provision;

    @Comment("방문 가능 요일")
    private String day;

    @Comment("방문 가능 시간")
    private String visitingTime;

    @Comment("예약 방법")
    private String reservationDescription;

    //todo : 키워드 배열 어떻게 넘길 건지
    //Json 형식 String으로 DB에 밀어 넣기

    @Comment("필수 키워드")
    private String keyword;

    @Comment("추가 키워드")
    private String additionalKeyword;

    @Comment("최소 글자 수")
    private int minTextNum;

    @Comment("최소 이미지 수")
    private int minImageNum;

    @Comment("지도 삽입 여부")
    private Boolean isMap;

    @Comment("기타 특이 사항")
    private String etcComment;

    @Comment("주의 사항")
    private String notandum;

    //todo : 추가 입력 Enum Type

    @Comment("성별")
    private Gender gender;

    @ElementCollection
    @CollectionTable(name = "campaign_images", joinColumns = @JoinColumn(name = "campaign_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private List<CampaignAge> campaignAges = new ArrayList<>();

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private List<CampaignJob> campaignJobs = new ArrayList<>();

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private List<CampaignType> campaignTypes = new ArrayList<>();

    @Builder
    public Campaign(Market market, List<String> imageUrls, String campaignName, LocalDateTime applyStartTime, LocalDateTime applyEndTime, LocalDateTime resultAnnouncementTime, LocalDateTime registrationStartTime, LocalDateTime registrationEndTime, String provision, String day, String visitingTime, String reservationDescription, String keyword, String additionalKeyword, int minTextNum, int minImageNum, boolean isMap, String etcComment, String notandum, Gender gender, List<CampaignAge> campaignAges, List<CampaignJob> campaignJobs, List<CampaignType> campaignTypes) {
        this.market = market;
        this.imageUrls = imageUrls;
        this.campaignName = campaignName;
        this.applyStartTime = applyStartTime;
        this.applyEndTime = applyEndTime;
        this.resultAnnouncementTime = resultAnnouncementTime;
        this.registrationStartTime = registrationStartTime;
        this.registrationEndTime = registrationEndTime;
        this.provision = provision;
        this.day = day;
        this.visitingTime = visitingTime;
        this.reservationDescription = reservationDescription;
        this.keyword = keyword;
        this.additionalKeyword = additionalKeyword;
        this.minTextNum = minTextNum;
        this.minImageNum = minImageNum;
        this.isMap = isMap;
        this.etcComment = etcComment;
        this.notandum = notandum;
        this.gender= gender;
        this.campaignAges = campaignAges;
        this.campaignJobs = campaignJobs;
        this.campaignTypes = campaignTypes;
    }

    public void registrationCampaignMission(CampaignMissionRequestDto campaignMissionRequestDto) {
        keyword = campaignMissionRequestDto.getKeyword();
        additionalKeyword = campaignMissionRequestDto.getAdditionalKeyword();
        minTextNum = campaignMissionRequestDto.getMinTextNum();
        minImageNum = campaignMissionRequestDto.getMinImageNum();
        isMap = campaignMissionRequestDto.getIsMap();
        etcComment = campaignMissionRequestDto.getEtcComment();
        notandum = campaignMissionRequestDto.getNotandum();
    }

    public void registrationCampaignGender(Gender getGender) {
        gender = getGender;
    }

    public List<CampaignAge> getCampaignAges() {
        return Collections.unmodifiableList(campaignAges);
    }

    public List<CampaignJob> getCampaignJobs() {
        return Collections.unmodifiableList(campaignJobs);
    }

    public List<CampaignType> getCampaignTypes() {
        return Collections.unmodifiableList(campaignTypes);
    }

}
