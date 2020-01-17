package com.itsol.train.mock.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "issue_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class IssueHistoryEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTO_INCRE_SEQ_ISSUE_HIS")
    @SequenceGenerator(name = "AUTO_INCRE_SEQ_ISSUE_HIS", sequenceName = "AUTO_INCRE_SEQ_ISSUE_HIS", allocationSize = 1)
    Long id;

    @Column(name = "update_date", nullable = false)
    Date updateDate;

    @Column(name = "comments", nullable = false)
    String comments;

    @Column(name = "issue_change", nullable = false)
    String issueChange;

    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    IssueEntity issueEntity;

    @ManyToOne
    @JoinColumn(name = "update_person_id", nullable = false)
    EmployeeEntity employeeEntity;
}
