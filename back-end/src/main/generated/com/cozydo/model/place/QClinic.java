package com.cozydo.model.place;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QClinic is a Querydsl query type for Clinic
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClinic extends EntityPathBase<Clinic> {

    private static final long serialVersionUID = 812467903L;

    public static final QClinic clinic = new QClinic("clinic");

    public final StringPath city = createString("city");

    public final StringPath clinicAddress = createString("clinicAddress");

    public final NumberPath<Integer> clinicIdx = createNumber("clinicIdx", Integer.class);

    public final StringPath clinicLat = createString("clinicLat");

    public final StringPath clinicLon = createString("clinicLon");

    public final StringPath clinicName = createString("clinicName");

    public final StringPath gu = createString("gu");

    public final StringPath sat = createString("sat");

    public final StringPath sun = createString("sun");

    public final StringPath tel = createString("tel");

    public final StringPath week = createString("week");

    public QClinic(String variable) {
        super(Clinic.class, forVariable(variable));
    }

    public QClinic(Path<? extends Clinic> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClinic(PathMetadata metadata) {
        super(Clinic.class, metadata);
    }

}

