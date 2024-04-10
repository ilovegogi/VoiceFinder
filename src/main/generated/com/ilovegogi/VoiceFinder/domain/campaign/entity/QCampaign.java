package com.ilovegogi.VoiceFinder.domain.campaign.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCampaign is a Querydsl query type for Campaign
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCampaign extends EntityPathBase<Campaign> {

    private static final long serialVersionUID = 367389275L;

    public static final QCampaign campaign = new QCampaign("campaign");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCampaign(String variable) {
        super(Campaign.class, forVariable(variable));
    }

    public QCampaign(Path<? extends Campaign> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCampaign(PathMetadata metadata) {
        super(Campaign.class, metadata);
    }

}

