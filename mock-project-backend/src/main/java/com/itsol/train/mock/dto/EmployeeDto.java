package com.itsol.train.mock.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itsol.train.mock.entity.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDto extends BaseDto implements Serializable {

    @NotNull
    String login;
    @NotNull
    String password;

    Long id;

    String username;

    String fullName;
    String imageUrl;
    Date lastAccess;
    Date createdDate;
    String phoneNumber;
    String userType;
    String address;
    String university;
    Integer graduatedYear;
    Boolean isLeader;
    Boolean isManager;
    Boolean isActived;
    Date birthday;
    String skypeAccount;
    String email;
    Boolean isApproved;

    Long roleId;
    String roleName;

    Long departmentId;
    String departmentName;

    Long positionId;
    String positionName;

    Long teamId;
    String teamName;

    Boolean rememberMe;
    List<String> authorities;

//    Set<RoleEntity> roleEntities;
//    DepartmentEntity departmentEntity;
//    PositionEntity positionEntity;
//    TeamEntity teamEntity;

//    public EmployeeDto(EmployeeEntity employeeEntity) {
//        if (employeeEntity != null) {
//            this.id = employeeEntity.getId();
//            this.username = employeeEntity.getUsername();
//            this.email = employeeEntity.getEmail();
//            this.roleEntities = employeeEntity.getRoleEntities();
//            this.authorities = employeeEntity.getRoleEntities().stream()
//                    .map(auth -> ((RoleEntity) auth).getName()).collect(Collectors.toList());
//            this.departmentEntity = employeeEntity.getDepartmentEntity();
//            this.positionEntity = employeeEntity.getPositionEntity();
//        }
//    }
}
