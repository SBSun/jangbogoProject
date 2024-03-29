package backend.jangbogoProject.entity.commodity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommodity is a Querydsl query type for Commodity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommodity extends EntityPathBase<Commodity> {

    private static final long serialVersionUID = -966737771L;

    public static final QCommodity commodity = new QCommodity("commodity");

    public final StringPath A_PRICE = createString("A_PRICE");

    public final StringPath A_UNIT = createString("A_UNIT");

    public final StringPath ADD_COL = createString("ADD_COL");

    public final NumberPath<Long> category_id = createNumber("category_id", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> M_SEQ = createNumber("M_SEQ", Long.class);

    public final StringPath P_DATE = createString("P_DATE");

    public QCommodity(String variable) {
        super(Commodity.class, forVariable(variable));
    }

    public QCommodity(Path<? extends Commodity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommodity(PathMetadata metadata) {
        super(Commodity.class, metadata);
    }

}

