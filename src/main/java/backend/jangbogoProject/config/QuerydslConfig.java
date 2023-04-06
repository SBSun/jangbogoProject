package backend.jangbogoProject.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QuerydslConfig {

    /*
    * EntityManager를 빈으로 주입할 때 사용하는 어노테이션
    * 빈으로 주입받을 때 EntityManager의 경우 @Autowired가 아니라 해당 어노테이션으로 주입
    */
    @PersistenceContext
    private EntityManager entityManager;

    // JPAQueryFactory를 Bean으로 등록해서 프로젝트 전역에서 사용할 수 있도록 한다
    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }
}
