package backend.jangbogoProject.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryRequestDTO {
    private String name;
    private String parentName;
}
