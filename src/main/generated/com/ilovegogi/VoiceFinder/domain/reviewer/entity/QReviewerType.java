package com.ilovegogi.VoiceFinder.domain.reviewer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewerType is a Querydsl query type for ReviewerType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewerType extends EntityPathBase<ReviewerType> {

    private static final long serialVersionUID = -342764769L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewerType reviewerType = new QReviewerType("reviewerType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QReviewer reviewer;

    public final com.ilovegogi.VoiceFinder.domain.campaign.entity.QType type;

    public QReviewerType(String variable) {
        this(ReviewerType.class, forVariable(variable), INITS);
    }

    public QReviewerType(Path<? extends ReviewerType> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewerType(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewerType(PathMetadata metadata, PathInits inits) {
        this(ReviewerType.class, metadata, inits);
    }

    public QReviewerType(Class<? extends ReviewerType> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reviewer = inits.isInitialized("reviewer") ? new QReviewer(forProperty("reviewer"), inits.get("reviewer")) : null;
        this.type = inits.isInitialized("type") ? new com.ilovegogi.VoiceFinder.domain.campaign.entity.QType(forProperty("type")) : null;
    }

}

