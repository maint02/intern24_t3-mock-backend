package com.itsol.train.mock.dao;

import com.itsol.train.mock.dto.EmployeeDto;
import com.itsol.train.mock.dto.TeamProjectDTO;

import java.util.List;

public interface TeamProjectDAO {
    boolean save(TeamProjectDTO teamProjectDTO);
    List<TeamProjectDTO> getAllByProjectId(Long id);
    List<TeamProjectDTO> getALlByTeamId(Long id);
    List<EmployeeDto> getMemberByProjectId(Long id);
}
