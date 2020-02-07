package com.itsol.train.mock.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IssueSearchDTO {
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

    private Long employeeReportedId;
}
