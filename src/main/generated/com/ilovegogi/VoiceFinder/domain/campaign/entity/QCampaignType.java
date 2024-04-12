package com.ilovegogi.VoiceFinder.domain.campaign.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCampaignType is a Querydsl query type for CampaignType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCampaignType extends EntityPathBase<CampaignType> {

    private static final long serialVersionUID = -2113189835L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCampaignType campaignType = new QCampaignType("campaignType");

    public final QCampaign campaign;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QType type;

    public QCampaignType(String variable) {
        this(CampaignType.class, forVariable(variable), INITS);
    }

    public QCampaignType(Path<? extends CampaignType> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCampaignType(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCampaignType(PathMetadata metadata, PathInits inits) {
        this(CampaignType.class, metadata, inits);
    }

    public QCampaignType(Class<? extends CampaignType> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.campaign = inits.isInitialized("campaign") ? new QCampaign(forProperty("campaign"), inits.get("campaign")) : null;
        this.type = inits.isInitialized("type") ? new QType(forProperty("type")) : null;
    }

}

