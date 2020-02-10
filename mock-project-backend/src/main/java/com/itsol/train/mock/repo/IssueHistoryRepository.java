package com.itsol.train.mock.repo;

import com.itsol.train.mock.dto.IssueHistoryDTO;
import com.itsol.train.mock.entity.IssueEntity;
import com.itsol.train.mock.entity.IssueHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueHistoryRepository extends JpaRepository<IssueHistoryEntity,Long> {
    IssueHistoryEntity save(IssueHistoryEntity issueHistoryEntity);
    List<IssueHistoryEntity> getAllByIssueEntity(IssueEntity issueEntity);
}



