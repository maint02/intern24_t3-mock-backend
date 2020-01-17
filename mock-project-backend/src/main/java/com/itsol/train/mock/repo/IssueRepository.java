package com.itsol.train.mock.repo;

import com.itsol.train.mock.entity.IssueEntity;
import com.itsol.train.mock.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<IssueEntity,Long>{
    IssueEntity save(IssueEntity issueEntity);
    IssueEntity getById(Long id);
    void deleteById(Long id);
    Page<IssueEntity> getAllByProjectEntity(ProjectEntity projectEntity, Pageable pageable);
}
