package com.itsol.train.mock.repo;

import com.itsol.train.mock.entity.IssueHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueHistoryRepository extends JpaRepository<IssueHistoryEntity,Long> {
    IssueHistoryEntity save(IssueHistoryEntity issueHistoryEntity);
}
