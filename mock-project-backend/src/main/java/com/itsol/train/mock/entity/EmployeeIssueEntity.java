package com.itsol.train.mock.entity;

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
    @MapsId("employeeId")
    EmployeeEntity employeeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("issueId")
    IssueEntity issueEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    StatusEntity statusEntity;

    @Column(name = "spent_time", nullable = false)
    Float spentTime;

    @Column(name = "note")
    String note;


}
