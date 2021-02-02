package com.cozydo.model.place;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlace is a Querydsl query type for Place
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlace extends EntityPathBase<Place> {

    private static final long serialVersionUID = 1285132368L;

    public static final QPlace place = new QPlace("place");

    public final StringPath addressName = createString("addressName");

    public final StringPath dong = createString("dong");

    public final StringPath gu = createString("gu");

    public final NumberPath<Integer> placeIdx = createNumber("placeIdx", Integer.class);

    public final StringPath placeLat = createString("placeLat");

    public final StringPath placeLon = createString("placeLon");

    public final StringPath placeName = createString("placeName");

    public final StringPath roadAddressName = createString("roadAddressName");

    public final StringPath subCategory = createString("subCategory");

    public QPlace(String variable) {
        super(Place.class, forVariable(variable));
    }

    public QPlace(Path<? extends Place> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlace(PathMetadata metadata) {
        super(Place.class, metadata);
    }

}

