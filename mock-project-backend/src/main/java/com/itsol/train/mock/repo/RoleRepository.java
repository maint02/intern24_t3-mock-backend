package com.itsol.train.mock.repo;

import com.itsol.train.mock.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository {
    RoleEntity findByDefault();
    RoleEntity findByRoleName(String roleName);
}
