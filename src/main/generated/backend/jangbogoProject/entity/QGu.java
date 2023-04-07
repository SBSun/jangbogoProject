package backend.jangbogoProject.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGu is a Querydsl query type for Gu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGu extends EntityPathBase<Gu> {

    private static final long serialVersionUID = -2106581275L;

    public static final QGu gu = new QGu("gu");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QGu(String variable) {
        super(Gu.class, forVariable(variable));
    }

    public QGu(Path<? extends Gu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGu(PathMetadata metadata) {
        super(Gu.class, metadata);
    }

}

