package backend.jangbogoProject.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMarket is a Querydsl query type for Market
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMarket extends EntityPathBase<Market> {

    private static final long serialVersionUID = -2027629901L;

    public static final QMarket market = new QMarket("market");

    public final NumberPath<Integer> gu_id = createNumber("gu_id", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QMarket(String variable) {
        super(Market.class, forVariable(variable));
    }

    public QMarket(Path<? extends Market> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMarket(PathMetadata metadata) {
        super(Market.class, metadata);
    }

}

