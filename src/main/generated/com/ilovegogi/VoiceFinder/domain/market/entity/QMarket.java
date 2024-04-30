package com.ilovegogi.VoiceFinder.domain.market.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMarket is a Querydsl query type for Market
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMarket extends EntityPathBase<Market> {

    private static final long serialVersionUID = -1489876493L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMarket market = new QMarket("market");

    public final com.ilovegogi.VoiceFinder.global.entity.QTimestamped _super = new com.ilovegogi.VoiceFinder.global.entity.QTimestamped(this);

    public final StringPath address = createString("address");

    public final ListPath<com.ilovegogi.VoiceFinder.domain.campaign.entity.Campaign, com.ilovegogi.VoiceFinder.domain.campaign.entity.QCampaign> campaigns = this.<com.ilovegogi.VoiceFinder.domain.campaign.entity.Campaign, com.ilovegogi.VoiceFinder.domain.campaign.entity.QCampaign>createList("campaigns", com.ilovegogi.VoiceFinder.domain.campaign.entity.Campaign.class, com.ilovegogi.VoiceFinder.domain.campaign.entity.QCampaign.class, PathInits.DIRECT2);

    public final EnumPath<Category> category = createEnum("category", Category.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<String, StringPath> imageUrls = this.<String, StringPath>createList("imageUrls", String.class, StringPath.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final NumberPath<Long> ownerId = createNumber("ownerId", Long.class);

    public final com.ilovegogi.VoiceFinder.domain.user.entity.QUser user;

    public final StringPath wayDescription = createString("wayDescription");

    public QMarket(String variable) {
        this(Market.class, forVariable(variable), INITS);
    }

    public QMarket(Path<? extends Market> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMarket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMarket(PathMetadata metadata, PathInits inits) {
        this(Market.class, metadata, inits);
    }

    public QMarket(Class<? extends Market> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.ilovegogi.VoiceFinder.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

