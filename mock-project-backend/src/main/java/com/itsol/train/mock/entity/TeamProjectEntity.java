package com.itsol.train.mock.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "team_project")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamProjectEntity implements Serializable {
    @EmbeddedId
    TeamProjectPK teamProjectPK;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("teamId")
    TeamEntity teamEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    ProjectEntity projectEntity;

    @Column(name = "start_date", nullable = false)
    Date startDate;

    @Column(name = "handover_date", nullable = false)
    Date handoverDate;
}
