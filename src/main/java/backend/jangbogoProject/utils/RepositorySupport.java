package backend.jangbogoProject.utils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class RepositorySupport {
    
    public static <T> BooleanExpression toEq(final SimpleExpression<T> simpleExpression, final T compared){
        if(Objects.isNull(compared)){
            return null;
        }
        return simpleExpression.eq(compared);
    }

    public static <T> BooleanExpression toEq(final SimpleExpression<T> simpleExpression, final SimpleExpression<T> compared){
        if(Objects.isNull(compared)){
            return null;
        }
        return simpleExpression.eq(compared);
    }

    public static <T> BooleanExpression toNe(final SimpleExpression<T> simpleExpression, final T compared){
        if(Objects.isNull(compared)){
            return null;
        }
        return simpleExpression.ne(compared);
    }

    public static <T> BooleanExpression toNe(final SimpleExpression<T> simpleExpression, final SimpleExpression<T> compared){
        if(Objects.isNull(compared)){
            return null;
        }
        return simpleExpression.ne(compared);
    }

    public static BooleanExpression containsKeyword(final StringPath stringPath, final String keyword) {
        if (StringUtils.hasText(keyword)) {
            return null;
        }
        return stringPath.contains(keyword);
    }
}
