package com.itsol.train.mock.dto;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class StatusDTO {
    private Long id;
    private String name;
    private StatusTypeDTO statusTypeDTO;
}
