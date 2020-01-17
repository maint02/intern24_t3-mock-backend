package com.itsol.train.mock.dto;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class IssueDTO {
    private Long id;

    private Date startDate;

    private Long dueDate;

    private String name;

    private Long donePercent;

    private String priority;

    private String reason;

    private String description;

    private String type;

    private Long projectId;

    private Long statusId;

    private ProjectDTO projectDTO;
    private StatusDTO statusDTO;
    private Long employeeReportedId;
}
