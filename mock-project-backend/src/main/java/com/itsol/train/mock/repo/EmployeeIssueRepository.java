package com.itsol.train.mock.repo;

import com.itsol.train.mock.entity.EmpIssuePK;
import com.itsol.train.mock.entity.EmployeeIssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeIssueRepository extends JpaRepository<EmployeeIssueEntity, EmpIssuePK> {
    EmployeeIssueEntity save(EmployeeIssueEntity employeeIssueEntity);
    EmployeeIssueEntity getByEmpIssuePK(EmpIssuePK empIssuePK);
    void deleteByEmpIssuePK(EmpIssuePK empIssuePK);
}
