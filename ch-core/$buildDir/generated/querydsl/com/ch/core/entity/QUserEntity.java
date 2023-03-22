package com.ch.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = 1574999942L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final ComparablePath<java.util.UUID> createId = createComparable("createId", java.util.UUID.class);

    public final StringPath createIp = createString("createIp");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final BooleanPath delFlag = createBoolean("delFlag");

    public final StringPath email = createString("email");

    public final BooleanPath lockFlag = createBoolean("lockFlag");

    public final StringPath password = createString("password");

    public final ListPath<String, StringPath> roles = this.<String, StringPath>createList("roles", String.class, StringPath.class, PathInits.DIRECT2);

    public final ComparablePath<java.util.UUID> updateId = createComparable("updateId", java.util.UUID.class);

    public final StringPath updateIp = createString("updateIp");

    public final DateTimePath<java.time.LocalDateTime> updateTime = createDateTime("updateTime", java.time.LocalDateTime.class);

    public final ComparablePath<java.util.UUID> userCode = createComparable("userCode", java.util.UUID.class);

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

