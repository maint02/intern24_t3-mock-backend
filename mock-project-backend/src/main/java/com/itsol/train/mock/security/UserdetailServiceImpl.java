package com.itsol.train.mock.security;

import com.itsol.train.mock.entity.EmployeeEntity;
import com.itsol.train.mock.repo.EmployeeRepositoryJpa;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Component("userDetailsService")
public class UserdetailServiceImpl implements UserDetailsService {

    private Logger log = LoggerFactory.getLogger(UserdetailServiceImpl.class);

    private final EmployeeRepositoryJpa employeeRepositoryJpa;

    public UserdetailServiceImpl(EmployeeRepositoryJpa employeeRepositoryJpa){
        this.employeeRepositoryJpa = employeeRepositoryJpa;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        log.trace("Service authenticate: {}", login);
        if (new EmailValidator().isValid(login, null)) {
            return employeeRepositoryJpa.findOneWithAuthoritiesByEmail(login)
                    .map(user -> createSpringSecurityUser(login, user))
                    .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " not found in the database"));
        }

        return employeeRepositoryJpa.findOneWithAuthoritiesByUsername(login)
                .map(user -> createSpringSecurityUser(login, user))
                .orElseThrow(() -> new UsernameNotFoundException("User " + login + " not found in the database"));

    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String login, EmployeeEntity employeeEntity) {
        if (!employeeEntity.getIsActived()) {
            throw new UserNotActivatedException("User " + login + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = employeeEntity.getRoleEntities()
                .stream().map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(employeeEntity.getUsername(),
                employeeEntity.getPassword(), grantedAuthorities);
    }
}
