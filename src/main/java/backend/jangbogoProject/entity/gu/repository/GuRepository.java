package backend.jangbogoProject.entity.gu.repository;

import backend.jangbogoProject.entity.gu.Gu;
import backend.jangbogoProject.entity.gu.dto.GuResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuRepository extends JpaRepository<Gu, Long>, GuRepositoryCustom {

}
