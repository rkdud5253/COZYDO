package com.cozydo.model.likes;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLikes is a Querydsl query type for Likes
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLikes extends EntityPathBase<Likes> {

    private static final long serialVersionUID = -1695102470L;

    public static final QLikes likes = new QLikes("likes");

    public final NumberPath<Integer> placeIdx = createNumber("placeIdx", Integer.class);

    public final NumberPath<Integer> userIdx = createNumber("userIdx", Integer.class);

    public QLikes(String variable) {
        super(Likes.class, forVariable(variable));
    }

    public QLikes(Path<? extends Likes> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLikes(PathMetadata metadata) {
        super(Likes.class, metadata);
    }

}

