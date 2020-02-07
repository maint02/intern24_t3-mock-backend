package com.itsol.train.mock.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTO_INCRE_SEQ_EMPLOYEE")
    @SequenceGenerator(name = "AUTO_INCRE_SEQ_EMPLOYEE", sequenceName = "AUTO_INCRE_SEQ_EMPLOYEE", allocationSize = 1)
    Long id;

    @Column(name = "username", nullable = false)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "fullname", nullable = false)
    String fullName;

    @Column(name = "image_url")
    String imageUrl;

    @Column(name = "last_access")
    Date lastAccess;

    @Column(name = "created_date")

    Date createdDate;

    @Column(name = "email", nullable = false, unique = true)
    String email;


    @Column(name = "phone_number",nullable = false)
    String phoneNumber;

    @Column(name = "skype_account", unique = true)

    String skypeAccount;

    @Column(name = "user_type", nullable = false)
    String userType;

    @Column(name = "address")
    String address;

    @Column(name = "university")
    String university;

    @Column(name = "is_leader")
    Boolean isLeader;

    @Column(name = "graduated_year")
    Integer graduatedYear;

    @Column(name = "is_manager")
    Boolean isManager;

    @Column(name = "is_actived")
    Boolean isActived;


    @Column(name = "is_approved")
    Boolean isApproved;

    @Column(name = "reason_reject")
    String reasonReject;

    @Column(name = "birthday",nullable = false)
    Date birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    PositionEntity positionEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    TeamEntity teamEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    DepartmentEntity departmentEntity;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinTable(
            name = "EMPLOYEE_ROLE",
            joinColumns = @JoinColumn(name = "EMPLOYEE_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    Set<RoleEntity> roleEntities;

    @Transient
    String originalPassword;
}
