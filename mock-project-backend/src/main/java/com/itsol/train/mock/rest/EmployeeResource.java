package com.itsol.train.mock.rest;


import com.itsol.train.mock.constants.AppConstants;
import com.itsol.train.mock.dto.ResponseDto;
import com.itsol.train.mock.dto.EmployeeDto;

import com.itsol.train.mock.entity.EmployeeEntity;
import com.itsol.train.mock.repo.EmployeeRepository;
import com.itsol.train.mock.repo.EmployeeRepositoryJpa;
import com.itsol.train.mock.service.EmployeeService;
import com.itsol.train.mock.service.MailService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import oracle.jdbc.proxy.annotation.Pre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/user")
// public class EmployeeResource {

//     private Logger log = LoggerFactory.getLogger(EmployeeResource.class);

//     @Autowired
//     private EmployeeService employeeService;

//     @PostMapping("/register")
//     public ResponseEntity<ResponseDto> register(@RequestBody EmployeeDto employeeDto){
//         log.trace("REST request to register user website: {}", employeeDto);
//         ResponseDto responseDto = new ResponseDto();
//         try{
//             employeeService.register(employeeDto);
//             responseDto.setResponseCode(AppConstants.RESPONSE_OK);
//         } catch (Exception exception){
//             responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
//             responseDto.setMessage(exception.getMessage());
//         }
//         return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
//     }
//     @GetMapping("/get-profile")
//     public ResponseEntity<EmployeeDto> getProfile(){
//         EmployeeDto employeeDto = employeeService.getEmployeeWithRoles()
//                 .map(EmployeeDto::new)
//                 .orElseThrow(() -> new AccountResourceException("User could not be found"));
//         return new ResponseEntity<>(employeeDto, HttpStatus.OK);
//     }

//     private static class AccountResourceException extends RuntimeException {
//         private AccountResourceException(String message) {
//             super(message);
//         }
//     }

// }
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/user")
public class EmployeeResource {

    Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    MailService mailService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestBody EmployeeDto employeeDto) {
        log.trace("REST request to register user website: {}", employeeDto);
        ResponseDto responseDto = new ResponseDto();
        try {
            EmployeeEntity byUsername = employeeService.findByUsername(employeeDto.getUsername());
            EmployeeEntity byEmail = employeeService.findByEmail(employeeDto.getEmail());
            if (byUsername != null) {
                responseDto.setResponseCode(AppConstants.EXISTED_USERNAME);
                responseDto.setMessage("Existed username");
                return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (byEmail != null) {
                responseDto.setResponseCode(AppConstants.EXISTED_EMAIL);
                responseDto.setMessage("Existed email");
                return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            EmployeeEntity employee = employeeService.createEmployee(employeeDto);
            responseDto.setResponseCode(AppConstants.RESPONSE_OK);
            mailService.sendActivationEmail(employee.getEmail(), employee.getUsername(), employee.getOriginalPassword());
            responseDto.setMessage("Check email to get password for your account");
        } catch (Exception exception) {
            responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
            responseDto.setMessage(exception.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

//        @GetMapping("/get-employee-info-by-username")
//    public EmployeeEntity getEmployeeInfo(@RequestParam String username) {
//        EmployeeEntity employeeInfo = getEmployeeInfo(username);
//        employeeInfo.setPassword(null);
//        return employeeInfo;
//    }


    //    sau khi login thành công sẽ gọi đến api này để lấy thông tin employee
    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") long id) {
        EmployeeDto employeeInfo = employeeService.getById(id);
        return new ResponseEntity<>(employeeInfo, HttpStatus.OK);
    }

//api search by username như id
//    @GetMapping("/get/{username}")
//    public ResponseEntity<EmployeeDto> getEmployeeByUsername(@PathVariable("username") String username) {
//        EmployeeDto employeeInfo = employeeService.getById(id);
//        return new ResponseEntity<>(employeeInfo, HttpStatus.OK);
//    }
// search bằng các điều kiện khác -> trả 1 list -> viết hàm search
//    @PostMapping("/")

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('HR')")
    public ResponseEntity<ResponseDto> deleteEmployeeById(@PathVariable("id") long id) {
        boolean deleteResult = employeeService.deleteById(id);
        ResponseDto responseDto = new ResponseDto();

        if (deleteResult) {
            responseDto.setResponseCode(AppConstants.RESPONSE_OK);
            responseDto.setMessage("Delete completed");
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
        responseDto.setMessage("Delete failed");
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

//    @PutMapping("/update")
//    @PreAuthorize("hasAnyRole('HR')")
//    public ResponseEntity<ResponseDto> updateEmployeeById(EmployeeDto employeeDto) {
//        ResponseDto responseDto = new ResponseDto();
//        boolean updateResult = employeeService.updateById(employeeDto);
//        if (updateResult) {
//            responseDto.setResponseCode(AppConstants.RESPONSE_OK);
//            responseDto.setMessage("Update ok");
//            return new ResponseEntity<>(responseDto, HttpStatus.OK);
//        }
//        responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
//        responseDto.setMessage("Update failed");
//        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
//    }


    // set isActived to 1
    @GetMapping(value = "/active-account/{username}")
    public ResponseEntity<ResponseDto> setActive(@PathVariable("username") String name) {
        boolean activeResult = employeeService.activeEmployee(name);
        ResponseDto responseDto = new ResponseDto();

        if (activeResult) {
            responseDto.setResponseCode(AppConstants.RESPONSE_OK);
            responseDto.setMessage("Account is activated");
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
        responseDto.setMessage("Activation failed");
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //      get all employee
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<EmployeeDto>> getAll() {
        List<EmployeeDto> allEmployee = employeeService.getAllEmployee();
        if (allEmployee == null) {
            log.error("can't get all employee");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(allEmployee, HttpStatus.OK);
    }

//    get list by params
}

