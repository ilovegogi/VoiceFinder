package com.ilovegogi.VoiceFinder.domain.business.entity;

import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "business")
public class Business extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    private String bossName;

    private String bizName;

    @Comment("사업체 등록 번호")
    private Long bizNum;

    @Comment("사업자등록증명원")
    private String bizFile;

    @Comment("서비스 이용 약관")
    private Boolean bizClause1;

    @Comment("개인정보 수집 동의")
    private Boolean bizClause2;
}
