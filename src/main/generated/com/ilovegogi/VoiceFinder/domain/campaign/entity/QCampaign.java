package com.ilovegogi.VoiceFinder.domain.campaign.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCampaign is a Querydsl query type for Campaign
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCampaign extends EntityPathBase<Campaign> {

    private static final long serialVersionUID = 367389275L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCampaign campaign = new QCampaign("campaign");

    public final StringPath additionalKeyword = createString("additionalKeyword");

    public final DateTimePath<java.time.LocalDateTime> applyEndTime = createDateTime("applyEndTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> applyStartTime = createDateTime("applyStartTime", java.time.LocalDateTime.class);

    public final ListPath<CampaignAge, QCampaignAge> campaignAges = this.<CampaignAge, QCampaignAge>createList("campaignAges", CampaignAge.class, QCampaignAge.class, PathInits.DIRECT2);

    public final ListPath<CampaignJob, QCampaignJob> campaignJobs = this.<CampaignJob, QCampaignJob>createList("campaignJobs", CampaignJob.class, QCampaignJob.class, PathInits.DIRECT2);

    public final ListPath<CampaignType, QCampaignType> campaignTypes = this.<CampaignType, QCampaignType>createList("campaignTypes", CampaignType.class, QCampaignType.class, PathInits.DIRECT2);

    public final StringPath day = createString("day");

    public final StringPath etcComment = createString("etcComment");

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isMap = createBoolean("isMap");

    public final StringPath keyword = createString("keyword");

    public final com.ilovegogi.VoiceFinder.domain.market.entity.QMarket market;

    public final NumberPath<Integer> minImageNum = createNumber("minImageNum", Integer.class);

    public final NumberPath<Integer> minTextNum = createNumber("minTextNum", Integer.class);

    public final StringPath notandum = createString("notandum");

    public final StringPath provision = createString("provision");

    public final DateTimePath<java.time.LocalDateTime> registrationEndTime = createDateTime("registrationEndTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> registrationStartTime = createDateTime("registrationStartTime", java.time.LocalDateTime.class);

    public final StringPath reservationDescription = createString("reservationDescription");

    public final DateTimePath<java.time.LocalDateTime> resultAnnouncementTime = createDateTime("resultAnnouncementTime", java.time.LocalDateTime.class);

    public final StringPath visitingTime = createString("visitingTime");

    public QCampaign(String variable) {
        this(Campaign.class, forVariable(variable), INITS);
    }

    public QCampaign(Path<? extends Campaign> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCampaign(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCampaign(PathMetadata metadata, PathInits inits) {
        this(Campaign.class, metadata, inits);
    }

    public QCampaign(Class<? extends Campaign> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.market = inits.isInitialized("market") ? new com.ilovegogi.VoiceFinder.domain.market.entity.QMarket(forProperty("market"), inits.get("market")) : null;
    }

}

