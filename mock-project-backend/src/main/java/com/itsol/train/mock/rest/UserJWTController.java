package com.itsol.train.mock.rest;

import com.itsol.train.mock.dto.EmployeeDto;
import com.itsol.train.mock.security.jwt.JWTFilter;
import com.itsol.train.mock.security.jwt.TokenProvider;
import com.itsol.train.mock.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api")
public class UserJWTController {
    private Logger log = LoggerFactory.getLogger(getClass());

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final TokenProvider tokenProvider;

    private EmployeeService employeeService;

    public UserJWTController(AuthenticationManagerBuilder authenticationManagerBuilder,
                             TokenProvider tokenProvider) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authorize(@Valid @RequestBody EmployeeDto employeeDto) {
        log.trace("REST request to authenticate user: {}", employeeDto);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(employeeDto.getLogin(), employeeDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = employeeDto.getRememberMe() == null ? false : employeeDto.getRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, String.format("Bearer %s", jwt));
        return new ResponseEntity<>(Collections.singletonMap("id_token", jwt), httpHeaders, HttpStatus.OK);
    }

    @PostMapping(value = "/resetPassword")
    public ResponseEntity<Boolean> resetPassword(@Valid @RequestBody EmployeeDto employeeDto) {
        try {
            boolean result = employeeService.resetPassword(employeeDto.getEmail());
            return new ResponseEntity<>(result,HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }


}
