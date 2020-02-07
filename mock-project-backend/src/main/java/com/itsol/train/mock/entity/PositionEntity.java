package com.itsol.train.mock.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "position")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PositionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTO_INCRE_SEQ_POSITION")
    @SequenceGenerator(name = "AUTO_INCRE_SEQ_POSITION", sequenceName = "AUTO_INCRE_SEQ_POSITION", allocationSize = 1)
    Long id;

    @Column(name = "name")
    String name;

}
