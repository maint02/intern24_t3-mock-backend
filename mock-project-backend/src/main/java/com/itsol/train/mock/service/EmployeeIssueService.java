package com.itsol.train.mock.service;

import com.itsol.train.mock.dto.EmployeeIssueDTO;

import java.util.List;

public interface EmployeeIssueService {
    boolean save(EmployeeIssueDTO employeeIssueDTO);
    List<EmployeeIssueDTO> getByIssueId(Long id);
    List<EmployeeIssueDTO> getByEmployeeId(Long id);
}
