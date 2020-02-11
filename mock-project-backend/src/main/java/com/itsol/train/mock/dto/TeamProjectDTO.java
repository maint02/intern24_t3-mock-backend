package com.itsol.train.mock.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TeamProjectDTO {
    private Long teamId;
    private Long projectId;
    private Date startDate;
    private Date handoverDate;
}
