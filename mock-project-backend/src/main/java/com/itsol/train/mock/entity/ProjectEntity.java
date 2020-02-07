package com.itsol.train.mock.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "project")
@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTO_INCRE_SEQ_PROJECT")
    @SequenceGenerator(name = "AUTO_INCRE_SEQ_PROJECT", sequenceName = "AUTO_INCRE_SEQ_PROJECT", allocationSize = 1)
    Long id;

    @Column(name = "name",nullable = false,unique = true)
    String name;

    @Column(name = "status_id", nullable = false)
    Long statusId;


}
