package com.itsol.train.mock.dto;

import com.itsol.train.mock.entity.EmployeeEntity;
import com.itsol.train.mock.entity.RoleEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDto {

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
    Boolean isLeader;
    Integer graduatedYear;
    Boolean isManager;
    Boolean isActived;
    Date birthday;
    String skypeAccount;
    String email;

    Boolean rememberMe;

    Set<RoleEntity> roleEntities;

    public EmployeeDto(EmployeeEntity employeeEntity) {
        if (employeeEntity != null) {
            this.id = employeeEntity.getId();
            this.username = employeeEntity.getUsername();
            this.email = employeeEntity.getEmail();
            this.roleEntities = employeeEntity.getRoleEntities();
        }
    }
}
