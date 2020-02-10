package com.itsol.train.mock.vm;

import com.itsol.train.mock.dto.BaseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeVm extends BaseDto implements Serializable {
    String username;
    String roleName;
    String departmentName;
    String positionName;
    String teamName;
    String userType;
    String university;
    String address;
    Integer graduatedYear;
    Integer roleId;

    String myRoleName;
    Integer myDepartmentId;

}
