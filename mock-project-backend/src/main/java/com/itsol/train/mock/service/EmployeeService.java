package com.itsol.train.mock.service;

import com.itsol.train.mock.dto.EmployeeDto;
import com.itsol.train.mock.entity.EmployeeEntity;
import com.itsol.train.mock.exception.EmailExistException;
import com.itsol.train.mock.exception.UsernameExistException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface EmployeeService {
    /**
     * Register user
     *
     * @param employeeDto UserDto
     */
    void register(EmployeeDto employeeDto) throws UsernameExistException, EmailExistException;

    /**
     *
     * @return Optional<User>
     */

    Optional<EmployeeEntity> getEmployeeWithRoles();
    boolean resetPassword(String email) throws UsernameNotFoundException;
}
