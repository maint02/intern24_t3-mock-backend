package com.itsol.train.mock.mapper;

import com.itsol.train.mock.dto.IssueDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueMapper implements RowMapper<IssueDTO> {
    @Override
    public IssueDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        IssueDTO issueDTO=new IssueDTO();
        issueDTO.setId(resultSet.getLong("id"));
        issueDTO.setName(resultSet.getString("name"));
        issueDTO.setStartDate(resultSet.getDate("start_date"));
        issueDTO.setDueDate(resultSet.getLong("due_date"));
        issueDTO.setDonePercent(resultSet.getLong("done_percent"));
        issueDTO.setPriority(resultSet.getString("priority"));
        issueDTO.setReason(resultSet.getString("reason"));
        issueDTO.setDescription(resultSet.getString("description"));
        issueDTO.setType(resultSet.getString("type"));
        issueDTO.setProjectId(resultSet.getLong("project_id"));
        issueDTO.setStatusId(resultSet.getLong("status_id"));
        issueDTO.setEmployeeReportedId(resultSet.getLong("employee_reported_id"));
        issueDTO.setStatusName(resultSet.getString("status_name")); // cái này phải join với bảng status
        return issueDTO;
    }
}
