package com.itsol.train.mock.repo;

import com.itsol.train.mock.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {
    ProjectEntity save(ProjectEntity projectEntity);
    ProjectEntity getById(Long id);
    List<ProjectEntity> findAll();
    void deleteById(Long id);
}
