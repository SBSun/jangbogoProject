package backend.jangbogoProject.entity.gu.repository;

import backend.jangbogoProject.entity.gu.dto.GuResponseDto;

import java.util.List;

public interface GuRepositoryCustom {

    public String findNameById(Long id);

    public List<GuResponseDto.Info> findAllGuInfo();
}
