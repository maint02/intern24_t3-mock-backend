package com.itsol.train.mock.service.impl;

import com.itsol.train.mock.dto.ProjectDTO;
import com.itsol.train.mock.entity.ProjectEntity;
import com.itsol.train.mock.repo.ProjectRepository;
import com.itsol.train.mock.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<ProjectDTO> getAll() {
        List<ProjectEntity> projectEntities= projectRepository.findAll();
        List<ProjectDTO> dtos=modelMapper.map(projectEntities, new TypeToken<List<ProjectDTO>>(){}.getType());
        return dtos;
    }
}
