package com.itsol.train.mock.dao.impl;

import com.itsol.train.mock.dao.AbstractBaseDAO;
import com.itsol.train.mock.dao.EmployeeIssueDAO;
import com.itsol.train.mock.dto.EmployeeIssueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class EmloyeeIssueDAOImpl extends AbstractBaseDAO implements EmployeeIssueDAO {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<EmployeeIssueDTO> getByIssueId(Long id) {
        String sql="select * from employee_issue where issue_id=:issueId";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("issueId",id);
        return namedParameterJdbcTemplate.query(sql,parameters,
                BeanPropertyRowMapper.newInstance(EmployeeIssueDTO.class));
    }

    @Override
    public List<EmployeeIssueDTO> getByEmployeeId(Long id) {
        String sql="select * from employee_issue where employee_id=:employeeId";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("employeeId",id);
        return namedParameterJdbcTemplate.query(sql,parameters,
                BeanPropertyRowMapper.newInstance(EmployeeIssueDTO.class));
    }
}
