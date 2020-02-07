package com.itsol.train.mock.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "issue")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IssueEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "AUTO_INCRE_SEQ_ISSUE")
    @SequenceGenerator(name = "AUTO_INCRE_SEQ_ISSUE", sequenceName = "AUTO_INCRE_SEQ_ISSUE", allocationSize = 1)
    Long id;

    @Column(name = "start_date", nullable = false)
    Date startDate;

    @Column(name = "due_date", nullable = false)
    Integer dueDate;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "done_percent", nullable = false)
    Long donePercent;
//     Float donePercent;

    @Column(name = "priority", nullable = false)
    String priority;

    @Column(name = "reason", nullable = false)
    String reason;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "type", nullable = false)
    String type;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    ProjectEntity projectEntity;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    StatusEntity statusEntity;


    @ManyToOne
    @JoinColumn(name = "employee_reported_id")
    EmployeeEntity employeeEntity;

}
