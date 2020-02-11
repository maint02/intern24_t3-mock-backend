package com.itsol.train.mock.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class BaseDto implements Serializable {
    Integer pageSize = 10;
    Integer page = 1;
    String sort;
}
