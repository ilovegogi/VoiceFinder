package com.ilovegogi.VoiceFinder.domain.campaign.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QType is a Querydsl query type for Type
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QType extends EntityPathBase<Type> {

    private static final long serialVersionUID = 39441925L;

    public static final QType type1 = new QType("type1");

    public final ListPath<CampaignType, QCampaignType> campaignTypes = this.<CampaignType, QCampaignType>createList("campaignTypes", CampaignType.class, QCampaignType.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.ilovegogi.VoiceFinder.domain.reviewer.entity.ReviewerType, com.ilovegogi.VoiceFinder.domain.reviewer.entity.QReviewerType> reviewerTypes = this.<com.ilovegogi.VoiceFinder.domain.reviewer.entity.ReviewerType, com.ilovegogi.VoiceFinder.domain.reviewer.entity.QReviewerType>createList("reviewerTypes", com.ilovegogi.VoiceFinder.domain.reviewer.entity.ReviewerType.class, com.ilovegogi.VoiceFinder.domain.reviewer.entity.QReviewerType.class, PathInits.DIRECT2);

    public final StringPath type = createString("type");

    public QType(String variable) {
        super(Type.class, forVariable(variable));
    }

    public QType(Path<? extends Type> path) {
        super(path.getType(), path.getMetadata());
    }

    public QType(PathMetadata metadata) {
        super(Type.class, metadata);
    }

}

