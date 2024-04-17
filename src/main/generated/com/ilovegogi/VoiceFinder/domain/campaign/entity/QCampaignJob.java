package com.ilovegogi.VoiceFinder.domain.campaign.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCampaignJob is a Querydsl query type for CampaignJob
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCampaignJob extends EntityPathBase<CampaignJob> {

    private static final long serialVersionUID = 1317295970L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCampaignJob campaignJob = new QCampaignJob("campaignJob");

    public final QCampaign campaign;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QJob job;

    public QCampaignJob(String variable) {
        this(CampaignJob.class, forVariable(variable), INITS);
    }

    public QCampaignJob(Path<? extends CampaignJob> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCampaignJob(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCampaignJob(PathMetadata metadata, PathInits inits) {
        this(CampaignJob.class, metadata, inits);
    }

    public QCampaignJob(Class<? extends CampaignJob> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.campaign = inits.isInitialized("campaign") ? new QCampaign(forProperty("campaign"), inits.get("campaign")) : null;
        this.job = inits.isInitialized("job") ? new QJob(forProperty("job"), inits.get("job")) : null;
    }

}

