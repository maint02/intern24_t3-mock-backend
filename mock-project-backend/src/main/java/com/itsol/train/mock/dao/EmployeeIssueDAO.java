package com.itsol.train.mock.dao;

import com.itsol.train.mock.dto.EmployeeIssueDTO;

import java.util.List;

public interface EmployeeIssueDAO {
    List<EmployeeIssueDTO> getByIssueId(Long id);
    List<EmployeeIssueDTO> getByEmployeeId(Long id);
}
