package com.cozydo.model.crawling;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCoronalevel is a Querydsl query type for Coronalevel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCoronalevel extends EntityPathBase<Coronalevel> {

    private static final long serialVersionUID = 1606009253L;

    public static final QCoronalevel coronalevel = new QCoronalevel("coronalevel");

    public final StringPath city = createString("city");

    public final StringPath level = createString("level");

    public QCoronalevel(String variable) {
        super(Coronalevel.class, forVariable(variable));
    }

    public QCoronalevel(Path<? extends Coronalevel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCoronalevel(PathMetadata metadata) {
        super(Coronalevel.class, metadata);
    }

}

