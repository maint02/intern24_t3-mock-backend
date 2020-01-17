package com.itsol.train.mock.entity;

import com.itsol.train.mock.dto.EmployeeIssueDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee_issue")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeIssueEntity implements Serializable {

    @EmbeddedId
    EmpIssuePK empIssuePK;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employee_id")
    EmployeeEntity employeeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("issue_id")
    IssueEntity issueEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    StatusEntity statusEntity;

    @Column(name = "spent_time", nullable = false)
    Long spentTime;

    @Column(name = "note")
    String note;

    @Column(name = "employee_assigned_id")
    Long employeeAssignId;

}
