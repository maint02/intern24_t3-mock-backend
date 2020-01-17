package com.itsol.train.mock.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)


public class RoleEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTO_INCRE_SEQ_ROLE")
    @SequenceGenerator(name = "AUTO_INCRE_SEQ_ROLE", sequenceName = "AUTO_INCRE_SEQ_ROLE", allocationSize = 1)
    Long id;

    @Column(name = "name")
    String name;


}
