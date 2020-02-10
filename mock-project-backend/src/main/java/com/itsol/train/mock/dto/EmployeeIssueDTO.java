package com.itsol.train.mock.dto;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class EmployeeIssueDTO {
    private Long employeeId;
    private Long issueId;
    private Float spentTime;
    private String note;
    private Long statusId;
    private Long employeeAssignedId;
}
