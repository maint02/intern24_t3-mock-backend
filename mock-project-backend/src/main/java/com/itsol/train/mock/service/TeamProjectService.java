package com.itsol.train.mock.service;

import com.itsol.train.mock.dto.EmployeeDto;
import com.itsol.train.mock.dto.TeamProjectDTO;

import java.util.List;

public interface TeamProjectService {
    boolean save(TeamProjectDTO teamProjectDTO);
    List<TeamProjectDTO> getAllByProjectId(Long id);
    List<TeamProjectDTO> getALlByTeamId(Long id);
    List<EmployeeDto> getAllIdMemberByProjectId(Long id);
}
