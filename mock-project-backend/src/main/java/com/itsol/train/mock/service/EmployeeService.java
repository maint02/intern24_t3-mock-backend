package com.itsol.train.mock.service;

import com.itsol.train.mock.dto.EmployeeDto;
import com.itsol.train.mock.dto.ResponseDto;
import com.itsol.train.mock.entity.EmployeeEntity;
import com.itsol.train.mock.exception.EmailExistException;
import com.itsol.train.mock.exception.UsernameExistException;
import com.itsol.train.mock.rest.EmployeeResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeEntity createEmployee(EmployeeDto employeeDto) throws UsernameExistException, EmailExistException;

    boolean resetPassword(String email) throws UsernameNotFoundException;

    EmployeeEntity findByUsername (String username);

    EmployeeEntity findByEmail (String email);

    boolean activeEmployee(String username);

    EmployeeEntity getEmployeeInfo(String username);

    EmployeeDto getById(long id);

    boolean deleteById (long id);

//    boolean updateById (EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployee();

    List<EmployeeDto> getListByUsername();


}
