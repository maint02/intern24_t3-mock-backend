package com.itsol.train.mock.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseDto {
    String message;
    int responseCode;
    Object dataResponse;
}
