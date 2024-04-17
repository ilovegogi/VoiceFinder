package com.ilovegogi.VoiceFinder.domain.campaign.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJob is a Querydsl query type for Job
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJob extends EntityPathBase<Job> {

    private static final long serialVersionUID = -968568942L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJob job1 = new QJob("job1");

    public final ListPath<CampaignJob, QCampaignJob> campaignJobs = this.<CampaignJob, QCampaignJob>createList("campaignJobs", CampaignJob.class, QCampaignJob.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath job = createString("job");

    public final com.ilovegogi.VoiceFinder.domain.reviewer.entity.QReviewer reviewer;

    public QJob(String variable) {
        this(Job.class, forVariable(variable), INITS);
    }

    public QJob(Path<? extends Job> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJob(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJob(PathMetadata metadata, PathInits inits) {
        this(Job.class, metadata, inits);
    }

    public QJob(Class<? extends Job> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reviewer = inits.isInitialized("reviewer") ? new com.ilovegogi.VoiceFinder.domain.reviewer.entity.QReviewer(forProperty("reviewer"), inits.get("reviewer")) : null;
    }

}

