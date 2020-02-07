package com.itsol.train.mock.repo;

import com.itsol.train.mock.dto.IssueDTO;
import com.itsol.train.mock.dto.IssueSearchDTO;
import com.itsol.train.mock.dto.PagingDataDTO;
import com.itsol.train.mock.entity.IssueEntity;
import org.springframework.data.domain.Pageable;

public interface IssueRepoCustom {
    Boolean save(IssueEntity issueEntity);
    IssueDTO getById(Long id);
    boolean deleteByIds(Long[] ids);
    PagingDataDTO getByProjectIdPaging(Long id, Pageable pageable);
    PagingDataDTO getByParams(IssueSearchDTO issueSearchDTO);
}
