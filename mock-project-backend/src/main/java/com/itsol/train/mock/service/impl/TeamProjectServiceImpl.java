package com.itsol.train.mock.service.impl;

import com.itsol.train.mock.dao.TeamProjectDAO;
import com.itsol.train.mock.dto.EmployeeDto;
import com.itsol.train.mock.dto.TeamProjectDTO;
import com.itsol.train.mock.service.TeamProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeamProjectServiceImpl implements TeamProjectService {
    @Autowired
    private TeamProjectDAO teamProjectDAO;
    @Override
    public boolean save(TeamProjectDTO teamProjectDTO) {
        return teamProjectDAO.save(teamProjectDTO);
    }

    @Override
    public List<TeamProjectDTO> getAllByProjectId(Long id) {
        return teamProjectDAO.getAllByProjectId(id);
    }

    @Override
    public List<TeamProjectDTO> getALlByTeamId(Long id) {
        return teamProjectDAO.getALlByTeamId(id);
    }

    @Override
    public List<EmployeeDto> getAllIdMemberByProjectId(Long id) {
        return teamProjectDAO.getMemberByProjectId(id);
    }
}
