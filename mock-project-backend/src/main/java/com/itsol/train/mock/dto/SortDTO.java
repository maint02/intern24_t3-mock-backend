package com.itsol.train.mock.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SortDTO {
    private static final String DESC = "DESC";
    private static final String ASC = "ASC";
    private String fieldName;
    private String orderBy;

    public String getOrderBy(){
        if(StringUtils.isBlank(orderBy))
            return ASC;
        return DESC.equals(orderBy.trim().toUpperCase()) ? DESC : ASC;
    }
}
