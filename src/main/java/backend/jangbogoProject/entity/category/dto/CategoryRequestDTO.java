package backend.jangbogoProject.entity.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {
    @NotBlank(message = "카테고리 이름은 필수 입력 값입니다.")
    private String name;
    private String parentName;
}
