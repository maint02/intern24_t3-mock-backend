package com.itsol.train.mock.rest;


import com.itsol.train.mock.constants.AppConstants;
import com.itsol.train.mock.dto.ResponseDto;
import com.itsol.train.mock.dto.EmployeeDto;
import com.itsol.train.mock.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class EmployeeResource {

    private Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestBody EmployeeDto employeeDto){
        log.trace("REST request to register user website: {}", employeeDto);
        ResponseDto responseDto = new ResponseDto();
        try{
            employeeService.register(employeeDto);
            responseDto.setResponseCode(AppConstants.RESPONSE_OK);
        } catch (Exception exception){
            responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
            responseDto.setMessage(exception.getMessage());
        }
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @GetMapping("/get-profile")
    public ResponseEntity<EmployeeDto> getProfile(){
        EmployeeDto employeeDto = employeeService.getEmployeeWithRoles()
                .map(EmployeeDto::new)
                .orElseThrow(() -> new AccountResourceException("User could not be found"));
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }

}
