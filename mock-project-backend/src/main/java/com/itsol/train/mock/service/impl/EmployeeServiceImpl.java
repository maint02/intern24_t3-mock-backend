package com.itsol.train.mock.service.impl;

import com.itsol.train.mock.dto.BaseSearchDTO;
import com.itsol.train.mock.dto.EmployeeDto;
import com.itsol.train.mock.entity.*;
import com.itsol.train.mock.exception.EmailExistException;
import com.itsol.train.mock.exception.UsernameExistException;
import com.itsol.train.mock.repo.*;
import com.itsol.train.mock.service.EmployeeService;
import com.itsol.train.mock.service.MailService;
import com.itsol.train.mock.utils.DataUtil;
import com.itsol.train.mock.vm.EmployeeVm;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.*;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    EmployeeRepositoryJpa employeeRepositoryJpa;

    @Autowired
    MailService mailService;

    @Override
    @Transactional
    public EmployeeEntity createEmployee(EmployeeDto employeeDto) throws UsernameExistException, EmailExistException {
        log.trace("REST request to register user website: {}", employeeDto);

        EmployeeEntity entity = modelMapper.map(employeeDto, EmployeeEntity.class);
        String randomString = DataUtil.generateRandomString(12);
        entity.setPassword(passwordEncoder.encode(randomString));
        entity.setOriginalPassword(randomString);
        entity.setIsActived(Boolean.FALSE);
        entity.setIsApproved(Boolean.FALSE);

        RoleEntity roleDefault = roleRepository.findByDefault();
        Set<RoleEntity> roleEntities = new HashSet<>();
        roleEntities.add(roleDefault);
        entity.setRoleEntities(roleEntities);

        DepartmentEntity departmentEntity = departmentRepository.findByDefault();
        entity.setDepartmentEntity(departmentEntity);

        TeamEntity teamEntity = teamRepository.findByDefault();
        entity.setTeamEntity(teamEntity);

        PositionEntity positionEntity = positionRepository.findByDefault();
        entity.setPositionEntity(positionEntity);
        EmployeeEntity employeeEntity = employeeRepositoryJpa.save(entity);
        return employeeEntity;
    }

    @Override
    public boolean resetPassword(String email) throws UsernameNotFoundException {
        if (!DataUtil.isNotNullAndEmptyString(email)) {
            throw new UsernameNotFoundException("Email '" + email + "' not found");
        }
        EmployeeEntity employeeEntityByEmail = employeeRepositoryJpa.findByEmail(email);
        if (employeeEntityByEmail == null) {
            throw new UsernameNotFoundException("Email '" + email + "' not found");
        }
        String randomString = DataUtil.generateRandomString(12);
        String newPassword = passwordEncoder.encode(randomString);
        employeeEntityByEmail.setPassword(newPassword);

        boolean result = employeeRepository.updateEmployeeEntity(employeeEntityByEmail);
        if (result) {
            mailService.sendResetPassword(email, randomString);
        }
        return false;
    }

    @Override
    public EmployeeEntity findByUsername(String username) {
        Optional<EmployeeEntity> checkUsername = employeeRepositoryJpa.findOneWithAuthoritiesByUsername(username);
        return checkUsername.isPresent() ? checkUsername.get() : null;
    }

    @Override
    public EmployeeEntity findByEmail(String email) {
        Optional<EmployeeEntity> checkEmail = employeeRepositoryJpa.findOneWithAuthoritiesByEmail(email);
        return checkEmail.isPresent() ? checkEmail.get() : null;
    }

    @Override
    public boolean activeEmployee(String username) {
        try {
            EmployeeEntity entity = employeeRepositoryJpa.findByUsername(username);
            entity.setIsActived(true);
            employeeRepositoryJpa.save(entity);
        } catch (Exception e) {
            log.error(e + "");
            return false;
        }
        return true;
    }

    @Override
    public EmployeeEntity getEmployeeInfo(String username) {
        EmployeeEntity jpaByUsername = employeeRepositoryJpa.findByUsername(username);
        return jpaByUsername;
    }

    @Override
    public EmployeeDto getById(long id) {
        EmployeeDto employeeEntityById = employeeRepository.findEmployeeById(id);
        return employeeEntityById;
    }

    @Override
    public boolean deleteById(long id) {
        boolean result = employeeRepository.deleteEmployeeById(id);
        return result;
    }

    @Override
    public BaseSearchDTO getListByParams(EmployeeVm employeeVm) {
        return employeeRepository.findListEmployeesByParams(employeeVm);
    }

//    @Override
//    public boolean updateById(EmployeeDto employeeDto) {
//        Long id = employeeDto.getId();
//        EmployeeEntity employeeEntityById = employeeRepository.getEmployeeEntityById(id);
//        if (employeeEntityById != null) {
//            EmployeeEntity entity = modelMapper.map(employeeDto, EmployeeEntity.class);
//            entity.setIsActived(employeeDto.getIsActived());
//            entity.setIsApproved(employeeDto.getIsApproved());
//            entity.setDepartmentEntity(employeeDto.getDepartmentEntity());
//            entity.setPositionEntity(employeeDto.getPositionEntity());
//            entity.setTeamEntity(employeeDto.getTeamEntity());
//            entity.setAddress(employeeDto.getAddress());
//            entity.setBirthday(employeeDto.getBirthday());
//            entity.setCreatedDate(employeeDto.getCreatedDate());
//            entity.setGraduatedYear(employeeDto.getGraduatedYear());
//            entity.setRoleEntities(employeeDto.getRoleEntities());
//            entity.setEmail(employeeDto.getEmail());
//            entity.setFullName(employeeDto.getFullName());
//            entity.setImageUrl(employeeDto.getImageUrl());
//            entity.setIsLeader(employeeDto.getIsLeader());
//            entity.setIsManager(employeeDto.getIsManager());
//            entity.setPhoneNumber(employeeDto.getPhoneNumber());
//            entity.setSkypeAccount(employeeDto.getSkypeAccount());
//            entity.setUniversity(employeeDto.getUniversity());
//            entity.setUserType(employeeDto.getUserType());
//            employeeRepositoryJpa.save(entity);
//            return true;
//        } else {
//            throw new UsernameNotFoundException("Employee has id" + id + "not found");
//        }
//    }

//    @Override
//    public Page<EmployeeDto> getAllEmployee() {
//        Page<EmployeeDto> allDtos = employeeRepository.getAll();
//        return allDtos;
//    }

}



