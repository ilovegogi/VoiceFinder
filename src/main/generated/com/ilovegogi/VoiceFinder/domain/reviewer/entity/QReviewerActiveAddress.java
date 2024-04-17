package com.ilovegogi.VoiceFinder.domain.reviewer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewerActiveAddress is a Querydsl query type for ReviewerActiveAddress
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewerActiveAddress extends EntityPathBase<ReviewerActiveAddress> {

    private static final long serialVersionUID = -560123959L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewerActiveAddress reviewerActiveAddress = new QReviewerActiveAddress("reviewerActiveAddress");

    public final QActiveAddress activeAddress;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QReviewer reviewer;

    public QReviewerActiveAddress(String variable) {
        this(ReviewerActiveAddress.class, forVariable(variable), INITS);
    }

    public QReviewerActiveAddress(Path<? extends ReviewerActiveAddress> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewerActiveAddress(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewerActiveAddress(PathMetadata metadata, PathInits inits) {
        this(ReviewerActiveAddress.class, metadata, inits);
    }

    public QReviewerActiveAddress(Class<? extends ReviewerActiveAddress> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activeAddress = inits.isInitialized("activeAddress") ? new QActiveAddress(forProperty("activeAddress")) : null;
        this.reviewer = inits.isInitialized("reviewer") ? new QReviewer(forProperty("reviewer"), inits.get("reviewer")) : null;
    }

}

