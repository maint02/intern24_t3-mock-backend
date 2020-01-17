package com.itsol.train.mock.service;

import com.itsol.train.mock.dto.*;
import org.springframework.data.domain.Pageable;

public interface IssueService  {
    IssueDTO save(IssueDTO issueDTO);
    IssueDTO getById(Long id);
    boolean deleteByIds(Long[] ids);
    PagingDataDTO getByProjectIdPaging(Long id, Pageable pageable);
    String update(UpdateIssueDTO updateIssueDTO);
}
