package backend.jangbogoProject.entity.category.repository;

import java.util.List;

public interface CategoryRepositoryCustom {

    List<String> findNamesByDepth(int depth);
}
