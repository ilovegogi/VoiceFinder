package com.ilovegogi.VoiceFinder.domain.reviewer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActiveAddress is a Querydsl query type for ActiveAddress
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QActiveAddress extends EntityPathBase<ActiveAddress> {

    private static final long serialVersionUID = 2048433614L;

    public static final QActiveAddress activeAddress1 = new QActiveAddress("activeAddress1");

    public final StringPath activeAddress = createString("activeAddress");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<ReviewerActiveAddress, QReviewerActiveAddress> reviewerActiveAddresses = this.<ReviewerActiveAddress, QReviewerActiveAddress>createList("reviewerActiveAddresses", ReviewerActiveAddress.class, QReviewerActiveAddress.class, PathInits.DIRECT2);

    public QActiveAddress(String variable) {
        super(ActiveAddress.class, forVariable(variable));
    }

    public QActiveAddress(Path<? extends ActiveAddress> path) {
        super(path.getType(), path.getMetadata());
    }

    public QActiveAddress(PathMetadata metadata) {
        super(ActiveAddress.class, metadata);
    }

}

