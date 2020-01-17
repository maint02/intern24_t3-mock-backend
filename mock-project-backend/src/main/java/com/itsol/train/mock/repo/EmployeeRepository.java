package com.itsol.train.mock.repo;

import com.itsol.train.mock.entity.EmployeeEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

    String USERS_BY_LOGIN_CACHE = "getByUsername";

//    @EntityGraph(attributePaths = "authorities")
//    @Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
    Optional<EmployeeEntity> findOneWithAuthoritiesByUsername(String username);

//    @EntityGraph(attributePaths = "authorities")
    Optional<EmployeeEntity> findOneWithAuthoritiesByEmail(String username);


}
