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

    public final EnumPath<Category> category = createEnum("category", Category.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.ilovegogi.VoiceFinder.domain.user.entity.QAddress location;

    public final StringPath name = createString("name");

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
        this.location = inits.isInitialized("location") ? new com.ilovegogi.VoiceFinder.domain.user.entity.QAddress(forProperty("location")) : null;
    }

}

