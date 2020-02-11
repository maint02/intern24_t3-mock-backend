package com.itsol.train.mock.rest;

import com.itsol.train.mock.constants.AppConstants;
import com.itsol.train.mock.dto.EmployeeIssueDTO;
import com.itsol.train.mock.dto.IssueHistoryDTO;
import com.itsol.train.mock.dto.ResponseDto;
import com.itsol.train.mock.service.EmployeeIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeIssueResource {
    @Autowired
    EmployeeIssueService employeeIssueService;

    @PostMapping("/employee-issue")
    public ResponseEntity<ResponseDto> addIssues(@RequestBody EmployeeIssueDTO employeeIssueDTO) {
        ResponseDto responseDto = new ResponseDto();
        try {
            boolean kq = employeeIssueService.save(employeeIssueDTO);
            if(kq){
                responseDto.setResponseCode(AppConstants.RESPONSE_OK);
                responseDto.setMessage("thêm employee issues thành công");
            }
            else {
                responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
                responseDto.setMessage("thêm employee issues thất bại");
            }
        } catch (Exception e) {
            responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
            responseDto.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @GetMapping("/employee-issue/get-by-employeeId/{id}")
    public ResponseEntity<ResponseDto> getByEmployeeId(@PathVariable(name = "id") Long id){
        ResponseDto responseDto = new ResponseDto();
        List<EmployeeIssueDTO> dtos =employeeIssueService.getByEmployeeId(id);
        if (dtos!=null){
            responseDto.setMessage("Get Issues Success");
            responseDto.setDataResponse(dtos);
            responseDto.setResponseCode(AppConstants.RESPONSE_OK);
        }else {
            responseDto.setMessage("Get Issues Error");
            responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
