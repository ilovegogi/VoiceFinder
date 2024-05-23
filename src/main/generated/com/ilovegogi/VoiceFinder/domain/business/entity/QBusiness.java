package com.ilovegogi.VoiceFinder.domain.business.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBusiness is a Querydsl query type for Business
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBusiness extends EntityPathBase<Business> {

    private static final long serialVersionUID = -815134789L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusiness business = new QBusiness("business");

    public final com.ilovegogi.VoiceFinder.global.entity.QTimestamped _super = new com.ilovegogi.VoiceFinder.global.entity.QTimestamped(this);

    public final BooleanPath bizClause1 = createBoolean("bizClause1");

    public final BooleanPath bizClause2 = createBoolean("bizClause2");

    public final StringPath bizFileUrl = createString("bizFileUrl");

    public final StringPath bizName = createString("bizName");

    public final NumberPath<Long> bizNum = createNumber("bizNum", Long.class);

    public final StringPath bossName = createString("bossName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.ilovegogi.VoiceFinder.domain.market.entity.Market, com.ilovegogi.VoiceFinder.domain.market.entity.QMarket> markets = this.<com.ilovegogi.VoiceFinder.domain.market.entity.Market, com.ilovegogi.VoiceFinder.domain.market.entity.QMarket>createList("markets", com.ilovegogi.VoiceFinder.domain.market.entity.Market.class, com.ilovegogi.VoiceFinder.domain.market.entity.QMarket.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.ilovegogi.VoiceFinder.domain.user.entity.QUser user;

    public QBusiness(String variable) {
        this(Business.class, forVariable(variable), INITS);
    }

    public QBusiness(Path<? extends Business> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBusiness(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBusiness(PathMetadata metadata, PathInits inits) {
        this(Business.class, metadata, inits);
    }

    public QBusiness(Class<? extends Business> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.ilovegogi.VoiceFinder.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

