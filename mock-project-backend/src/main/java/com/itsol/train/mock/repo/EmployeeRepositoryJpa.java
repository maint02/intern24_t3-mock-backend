package com.itsol.train.mock.repo;

import com.itsol.train.mock.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepositoryJpa extends JpaRepository<EmployeeEntity, String> {

//    String USERS_BY_LOGIN_CACHE = "getByUsername";

//    @EntityGraph(attributePaths = "authorities")
//    @Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
    Optional<EmployeeEntity> findOneWithAuthoritiesByUsername(String username);

//    @EntityGraph(attributePaths = "authorities")
    Optional<EmployeeEntity> findOneWithAuthoritiesByEmail(String email);

    EmployeeEntity findByEmail(String email);

    EmployeeEntity findByUsername(String username);


}
