package com.ilovegogi.VoiceFinder.domain.campaign.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAge is a Querydsl query type for Age
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAge extends EntityPathBase<Age> {

    private static final long serialVersionUID = -968577836L;

    public static final QAge age1 = new QAge("age1");

    public final StringPath age = createString("age");

    public final ListPath<CampaignAge, QCampaignAge> campaignAges = this.<CampaignAge, QCampaignAge>createList("campaignAges", CampaignAge.class, QCampaignAge.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QAge(String variable) {
        super(Age.class, forVariable(variable));
    }

    public QAge(Path<? extends Age> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAge(PathMetadata metadata) {
        super(Age.class, metadata);
    }

}

