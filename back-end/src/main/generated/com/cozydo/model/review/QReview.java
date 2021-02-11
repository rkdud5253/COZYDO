package com.cozydo.model.review;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -1868456482L;

    public static final QReview review = new QReview("review");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> placeIdx = createNumber("placeIdx", Integer.class);

    public final NumberPath<Long> reviewIdx = createNumber("reviewIdx", Long.class);

    public final NumberPath<Integer> reviewScore = createNumber("reviewScore", Integer.class);

    public final NumberPath<Integer> userIdx = createNumber("userIdx", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> writeTime = createDateTime("writeTime", java.time.LocalDateTime.class);

    public QReview(String variable) {
        super(Review.class, forVariable(variable));
    }

    public QReview(Path<? extends Review> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReview(PathMetadata metadata) {
        super(Review.class, metadata);
    }

}

