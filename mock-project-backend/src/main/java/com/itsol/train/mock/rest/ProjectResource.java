package com.itsol.train.mock.rest;

import com.itsol.train.mock.constants.AppConstants;
import com.itsol.train.mock.dto.ProjectDTO;
import com.itsol.train.mock.dto.ResponseDto;
import com.itsol.train.mock.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectResource {
    @Autowired
    ProjectService projectService;
    @GetMapping("/get-All")
    public ResponseEntity<ResponseDto> getAllProject(){
        ResponseDto responseDto=new ResponseDto();
        List<ProjectDTO> dtos=projectService.getAll();
        if (!dtos.isEmpty()){
            responseDto.setDataResponse(dtos);
            responseDto.setMessage("lấy dữ liệu thành công!");
            responseDto.setResponseCode(AppConstants.RESPONSE_OK);
        }else {
            responseDto.setMessage("không có dữ liệu trả về");
            responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
