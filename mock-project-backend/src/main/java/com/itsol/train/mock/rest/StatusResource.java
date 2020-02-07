package com.itsol.train.mock.rest;

import com.itsol.train.mock.constants.AppConstants;
import com.itsol.train.mock.dto.IssueDTO;
import com.itsol.train.mock.dto.ResponseDto;
import com.itsol.train.mock.dto.StatusDTO;
import com.itsol.train.mock.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatusResource {
    @Autowired
    StatusService statusService;

    @GetMapping("/status/get-by-typeId/{typeId}")
    public ResponseEntity<ResponseDto> getIssues(@PathVariable(name = "typeId") Long typeId) {
        ResponseDto responseDto = new ResponseDto();
        List<StatusDTO> list=statusService.getByTypeId(typeId);
        if (list.size()!=0){
            responseDto.setMessage("lấy thành công");
            responseDto.setResponseCode(AppConstants.RESPONSE_OK);
            responseDto.setDataResponse(list);
        }else {
            responseDto.setMessage("không lấy được dữ liệu");
            responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
        }
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.CREATED);
    }
}
