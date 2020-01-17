package com.itsol.train.mock.repo;

import com.itsol.train.mock.dto.EmployeeDto;
import com.itsol.train.mock.entity.EmployeeEntity;

import java.util.List;


public interface EmployeeRepository {


    boolean updateEmployeeEntity(EmployeeEntity employeeEntity);

    EmployeeEntity getEmployeeEntityById(long id);

    boolean insertEmployeeEntity(EmployeeEntity employeeEntity);

    boolean deleteEmployeeEntity(long id);

    List<EmployeeEntity> getAll();
}
