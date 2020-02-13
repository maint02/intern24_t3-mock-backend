package com.itsol.train.mock.dto;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class IssueHistoryDTO {
    Long id;
    Date updateDate;
    String comments;
    String issueChange;
    Long issueId;
    Long updatePersonId;
    Long donePercentOld;
    Long donePercentNew;
    String statusOld;
    String statusNew;
    String updatePersonName;
    String imageName;
}

