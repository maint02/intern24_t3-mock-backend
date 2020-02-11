package com.itsol.train.mock.dao.impl;

import com.itsol.train.mock.dao.AbstractBaseDAO;
import com.itsol.train.mock.dao.TeamProjectDAO;
import com.itsol.train.mock.dto.EmployeeDto;
import com.itsol.train.mock.dto.EmployeeIssueDTO;
import com.itsol.train.mock.dto.TeamProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class TeamProjectDAOImpl extends AbstractBaseDAO implements TeamProjectDAO {
        @Autowired
        private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public boolean save(TeamProjectDTO teamProjectDTO) {
        StringBuilder sql=new StringBuilder();
        sql.append("insert into team_project (team_id,project_id,start_date,handover_date) ");
        sql.append("values (:teamId,:projectId,:startDate,:handoverDate)");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("teamId",teamProjectDTO.getTeamId());
        parameters.put("projectId",teamProjectDTO.getProjectId());
        parameters.put("startDate",teamProjectDTO.getStartDate());
        parameters.put("handoverDate",teamProjectDTO.getHandoverDate());
        int kq = namedParameterJdbcTemplate.update(sql.toString(),parameters);
        return true?(kq!=0):false;
    }

    @Override
    public List<TeamProjectDTO> getAllByProjectId(Long id) {
        StringBuilder sql=new StringBuilder();
        sql.append("select * from team_project tp where tp.project_id=:projectId");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("projectId",id);
        return namedParameterJdbcTemplate.query(sql.toString(),parameters,
                BeanPropertyRowMapper.newInstance(TeamProjectDTO.class));
    }

    @Override
    public List<TeamProjectDTO> getALlByTeamId(Long id) {
        StringBuilder sql=new StringBuilder();
        sql.append("select * from team_project tp where tp.team_id=:teamId");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("teamId",id);
        return namedParameterJdbcTemplate.query(sql.toString(),parameters,
                BeanPropertyRowMapper.newInstance(TeamProjectDTO.class));
    }

    @Override
    public List<EmployeeDto> getMemberByProjectId(Long id) {
        StringBuilder sql=new StringBuilder();
        sql.append("select * from employee e ");
        sql.append("inner join team t on e.team_id=t.id ");
        sql.append("inner join team_project tp on t.id=tp.team_id where tp.project_id=:projectId ");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("projectId",id);
       return namedParameterJdbcTemplate.query(sql.toString(),parameters,
                BeanPropertyRowMapper.newInstance(EmployeeDto.class));
    }
}
