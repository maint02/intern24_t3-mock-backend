package com.itsol.train.mock.service;

import com.itsol.train.mock.dto.IssueHistoryDTO;

import java.util.List;

public interface IssueHistoryService {
    List<IssueHistoryDTO> getAllByIssueId(Long id);
}
