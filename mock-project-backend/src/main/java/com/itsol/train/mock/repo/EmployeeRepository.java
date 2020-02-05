package com.itsol.train.mock.repo;

import com.itsol.train.mock.dto.EmployeeDto;
import com.itsol.train.mock.entity.EmployeeEntity;

import java.util.List;


public interface EmployeeRepository {


    boolean updateEmployeeEntity(EmployeeEntity employeeEntity);

    EmployeeDto findEmployeeById(long id);

    boolean insertEmployeeEntity(EmployeeEntity employeeEntity);

    boolean deleteEmployeeById(long id);

    List<EmployeeDto> getAll();

    List<EmployeeDto> findListEmployeesByParams();

}
