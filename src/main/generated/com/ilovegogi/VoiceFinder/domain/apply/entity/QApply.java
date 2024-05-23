package com.ilovegogi.VoiceFinder.domain.apply.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApply is a Querydsl query type for Apply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApply extends EntityPathBase<Apply> {

    private static final long serialVersionUID = 1678180065L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApply apply = new QApply("apply");

    public final com.ilovegogi.VoiceFinder.global.entity.QTimestamped _super = new com.ilovegogi.VoiceFinder.global.entity.QTimestamped(this);

    public final com.ilovegogi.VoiceFinder.domain.campaign.entity.QCampaign campaign;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.ilovegogi.VoiceFinder.domain.reviewer.entity.QReviewer reviewer;

    public QApply(String variable) {
        this(Apply.class, forVariable(variable), INITS);
    }

    public QApply(Path<? extends Apply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApply(PathMetadata metadata, PathInits inits) {
        this(Apply.class, metadata, inits);
    }

    public QApply(Class<? extends Apply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.campaign = inits.isInitialized("campaign") ? new com.ilovegogi.VoiceFinder.domain.campaign.entity.QCampaign(forProperty("campaign"), inits.get("campaign")) : null;
        this.reviewer = inits.isInitialized("reviewer") ? new com.ilovegogi.VoiceFinder.domain.reviewer.entity.QReviewer(forProperty("reviewer"), inits.get("reviewer")) : null;
    }

}

