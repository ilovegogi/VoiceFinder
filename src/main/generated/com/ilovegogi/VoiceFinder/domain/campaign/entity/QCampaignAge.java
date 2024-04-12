package com.ilovegogi.VoiceFinder.domain.campaign.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCampaignAge is a Querydsl query type for CampaignAge
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCampaignAge extends EntityPathBase<CampaignAge> {

    private static final long serialVersionUID = 1317287076L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCampaignAge campaignAge = new QCampaignAge("campaignAge");

    public final QAge age;

    public final QCampaign campaign;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCampaignAge(String variable) {
        this(CampaignAge.class, forVariable(variable), INITS);
    }

    public QCampaignAge(Path<? extends CampaignAge> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCampaignAge(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCampaignAge(PathMetadata metadata, PathInits inits) {
        this(CampaignAge.class, metadata, inits);
    }

    public QCampaignAge(Class<? extends CampaignAge> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.age = inits.isInitialized("age") ? new QAge(forProperty("age")) : null;
        this.campaign = inits.isInitialized("campaign") ? new QCampaign(forProperty("campaign"), inits.get("campaign")) : null;
    }

}

