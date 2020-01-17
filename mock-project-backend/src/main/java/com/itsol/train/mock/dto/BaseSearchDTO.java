package com.itsol.train.mock.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseSearchDTO {
    private List<?> data;
    private long totalRecords;
    private int page;
    List<SortDTO> sorts;
    private int pageSize;
}
