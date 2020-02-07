package com.itsol.train.mock.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class DepartmentEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTO_INCRE_SEQ_DEPARTMENT")
    @SequenceGenerator(name = "AUTO_INCRE_SEQ_DEPARTMENT", sequenceName = "AUTO_INCRE_SEQ_DEPARTMENT", allocationSize = 1)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "location")
    String location;

}
