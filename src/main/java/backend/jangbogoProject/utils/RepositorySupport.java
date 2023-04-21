package backend.jangbogoProject.utils;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.SimpleExpression;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;
import java.util.stream.Collectors;

public class RepositorySupport {
    
    public static <T> BooleanExpression toEqExpression(final SimpleExpression<T> simpleExpression, final T compared){
        if(Objects.isNull(compared)){
            return null;
        }
        return simpleExpression.eq(compared);
    }

    public static <T> BooleanExpression toEqExpression(final SimpleExpression<T> simpleExpression, final SimpleExpression<T> compared){
        if(Objects.isNull(compared)){
            return null;
        }
        return simpleExpression.eq(compared);
    }

    public static <T> BooleanExpression toNeExpression(final SimpleExpression<T> simpleExpression, final T compared){
        if(Objects.isNull(compared)){
            return null;
        }
        return simpleExpression.ne(compared);
    }

    public static <T> BooleanExpression toNeExpression(final SimpleExpression<T> simpleExpression, final SimpleExpression<T> compared){
        if(Objects.isNull(compared)){
            return null;
        }
        return simpleExpression.ne(compared);
    }
}
