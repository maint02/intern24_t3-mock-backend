package com.itsol.train.mock.service;

import com.itsol.train.mock.dto.StatusDTO;

import java.util.List;

public interface StatusService {
    List<StatusDTO> getByTypeId(Long idType);
}
