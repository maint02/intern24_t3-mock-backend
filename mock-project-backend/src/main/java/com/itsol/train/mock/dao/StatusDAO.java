package com.itsol.train.mock.dao;

import com.itsol.train.mock.dto.StatusDTO;

import java.util.List;

public interface StatusDAO {
    List<StatusDTO> getByTypeId(Long idType);
}
