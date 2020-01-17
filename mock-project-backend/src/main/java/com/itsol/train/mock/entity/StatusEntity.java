package com.itsol.train.mock.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "status")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class StatusEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTO_INCRE_SEQ_STATUS")
    @SequenceGenerator(name = "AUTO_INCRE_SEQ_STATUS",sequenceName = "AUTO_INCRE_SEQ_STATUS", allocationSize = 1)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id",nullable = false)
    StatusTypeEntity statusTypeEntity;

}
