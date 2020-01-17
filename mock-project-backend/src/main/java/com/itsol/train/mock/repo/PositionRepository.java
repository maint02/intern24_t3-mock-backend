package com.itsol.train.mock.repo;

import com.itsol.train.mock.entity.PositionEntity;

public interface PositionRepository {
    PositionEntity findByDefault();
}
