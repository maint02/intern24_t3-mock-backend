package com.itsol.train.mock.service;

import com.itsol.train.mock.dto.*;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IssueService  {
    IssueDTO save(IssueDTO issueDTO);
    IssueDTO getById(Long id);
    boolean deleteByIds(Long[] ids);
    PagingDataDTO getByProjectIdPaging(Long id, Pageable pageable);
    String update(UpdateIssueDTO updateIssueDTO);
    PagingDataDTO getByParams(PagingDataDTO pagingDataDTO, IssueSearchDTO issueSearchDTO);
    Integer saveAllFromExel(MultipartFile file);
    Resource exportFileExelfromList(List<IssueDTO> dtos);
}
