package com.itsol.train.mock.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "status_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class StatusTypeEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTO_INCRE_SEQ_STATUS_TYPE")
    @SequenceGenerator(name = "AUTO_INCRE_SEQ_STATUS_TYPE", sequenceName = "AUTO_INCRE_SEQ_STATUS_TYPE", allocationSize = 1)
    Long id;

    @Column(name = "name")
    String name;
}
