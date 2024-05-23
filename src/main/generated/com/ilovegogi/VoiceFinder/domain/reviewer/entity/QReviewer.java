package com.ilovegogi.VoiceFinder.domain.reviewer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewer is a Querydsl query type for Reviewer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewer extends EntityPathBase<Reviewer> {

    private static final long serialVersionUID = -177571259L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewer reviewer = new QReviewer("reviewer");

    public final com.ilovegogi.VoiceFinder.global.entity.QTimestamped _super = new com.ilovegogi.VoiceFinder.global.entity.QTimestamped(this);

    public final ListPath<com.ilovegogi.VoiceFinder.domain.apply.entity.Apply, com.ilovegogi.VoiceFinder.domain.apply.entity.QApply> applies = this.<com.ilovegogi.VoiceFinder.domain.apply.entity.Apply, com.ilovegogi.VoiceFinder.domain.apply.entity.QApply>createList("applies", com.ilovegogi.VoiceFinder.domain.apply.entity.Apply.class, com.ilovegogi.VoiceFinder.domain.apply.entity.QApply.class, PathInits.DIRECT2);

    public final StringPath blogUrl = createString("blogUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.ilovegogi.VoiceFinder.domain.campaign.entity.QJob job;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final ListPath<ReviewerActiveAddress, QReviewerActiveAddress> reviewerActiveAddresses = this.<ReviewerActiveAddress, QReviewerActiveAddress>createList("reviewerActiveAddresses", ReviewerActiveAddress.class, QReviewerActiveAddress.class, PathInits.DIRECT2);

    public final BooleanPath reviewerClause1 = createBoolean("reviewerClause1");

    public final BooleanPath reviewerClause2 = createBoolean("reviewerClause2");

    public final ListPath<ReviewerType, QReviewerType> reviewerTypes = this.<ReviewerType, QReviewerType>createList("reviewerTypes", ReviewerType.class, QReviewerType.class, PathInits.DIRECT2);

    public final StringPath selfDescription = createString("selfDescription");

    public final com.ilovegogi.VoiceFinder.domain.user.entity.QUser user;

    public QReviewer(String variable) {
        this(Reviewer.class, forVariable(variable), INITS);
    }

    public QReviewer(Path<? extends Reviewer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewer(PathMetadata metadata, PathInits inits) {
        this(Reviewer.class, metadata, inits);
    }

    public QReviewer(Class<? extends Reviewer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.job = inits.isInitialized("job") ? new com.ilovegogi.VoiceFinder.domain.campaign.entity.QJob(forProperty("job"), inits.get("job")) : null;
        this.user = inits.isInitialized("user") ? new com.ilovegogi.VoiceFinder.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

