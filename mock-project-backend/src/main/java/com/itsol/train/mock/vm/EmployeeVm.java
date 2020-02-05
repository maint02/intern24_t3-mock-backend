package com.itsol.train.mock.vm;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeVm {
    String username;
    String roleName;
    String departmentName;
    String positionName;
    String teamName;
    String userType;
    String university;
    String address;
    Integer graduatedYear;
}
