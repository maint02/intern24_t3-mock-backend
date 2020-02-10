package com.itsol.train.mock.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class EmpIssuePK implements Serializable {


    @Column(name = "employee_id")
    Long employeeId;

    @Column(name = "issue_id")
    Long issueId;
}
