package com.itsol.train.mock.dto;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UpdateIssueDTO {
    private Long id;
    private Long donePercent;
    private Long statusId;
    private String comments;
    private Long updatePersonId;
}
