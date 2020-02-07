package com.itsol.train.mock.service.impl;

import com.itsol.train.mock.dao.StatusDAO;
import com.itsol.train.mock.dto.StatusDTO;
import com.itsol.train.mock.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    StatusDAO statusDAO;
    @Override
    public List<StatusDTO> getByTypeId(Long idType) {
        return statusDAO.getByTypeId(idType);
    }
}
