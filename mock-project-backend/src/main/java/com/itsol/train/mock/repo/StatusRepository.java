package com.itsol.train.mock.repo;

import com.itsol.train.mock.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusEntity,Long> {
    StatusEntity save(StatusEntity statusEntity);
    StatusEntity getById(Long id);
    void deleteById(Long id);
}
