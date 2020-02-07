package com.itsol.train.mock.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class BaseDto implements Serializable {
    Integer pageSize = 5;
    Integer page = 0;
    String sort;
}
