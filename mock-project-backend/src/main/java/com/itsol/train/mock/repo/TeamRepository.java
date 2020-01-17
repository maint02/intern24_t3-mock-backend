package com.itsol.train.mock.repo;

import com.itsol.train.mock.entity.TeamEntity;

public interface TeamRepository {
    TeamEntity findByDefault();
}
