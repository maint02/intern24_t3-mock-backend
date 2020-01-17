package com.itsol.train.mock.service.impl;

import com.itsol.train.mock.dto.EmployeeDto;
import com.itsol.train.mock.entity.*;
import com.itsol.train.mock.exception.EmailExistException;
import com.itsol.train.mock.exception.UsernameExistException;
import com.itsol.train.mock.repo.*;
import com.itsol.train.mock.security.SecurityUtils;
import com.itsol.train.mock.service.EmployeeService;
import com.itsol.train.mock.utils.DataUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void register(EmployeeDto employeeDto) throws UsernameExistException, EmailExistException {
        log.trace("Service to register user in web site");
        String username = employeeDto.getUsername();
        Optional<EmployeeEntity> checkUsername = employeeRepository.findOneWithAuthoritiesByUsername(username);
        if (checkUsername.isPresent()) {
            throw new UsernameExistException("Username has exist in database");
        }
        Optional<EmployeeEntity> checkEmail = employeeRepository.findOneWithAuthoritiesByEmail(username);
        if (checkEmail.isPresent()) {
            throw new EmailExistException("Email has exist in database");
        }
        EmployeeEntity entity = modelMapper.map(employeeDto, EmployeeEntity.class);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setIsActived(Boolean.TRUE);
        entity.setLastAccess(new Date());
        entity.setCreatedDate(new Date());

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
        employeeRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Optional<EmployeeEntity> getEmployeeWithRoles() {
        return SecurityUtils.getCurrentUserLogin().flatMap(employeeRepository::findOneWithAuthoritiesByUsername);
    }

    @Override
    @Transactional
    public boolean resetPassword(String email) throws UsernameNotFoundException {
        if (!DataUtil.isNotNullAndEmptyString(email)) {
            throw new UsernameNotFoundException("Email '" + email + "' not found");
        }
//        Optional<EmployeeDto> employeeDtoOptional =
        return false;
    }
}
