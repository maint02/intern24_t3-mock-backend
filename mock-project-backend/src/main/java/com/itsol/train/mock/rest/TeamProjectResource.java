package com.itsol.train.mock.rest;

import com.itsol.train.mock.constants.AppConstants;
import com.itsol.train.mock.dto.EmployeeDto;
import com.itsol.train.mock.dto.IssueDTO;
import com.itsol.train.mock.dto.ResponseDto;
import com.itsol.train.mock.dto.TeamProjectDTO;
import com.itsol.train.mock.service.TeamProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeamProjectResource {
    @Autowired
    private TeamProjectService teamProjectService;
    @PostMapping("/team-project")
    public ResponseEntity<ResponseDto> addIssues(@RequestBody TeamProjectDTO teamProjectDTO) {
        ResponseDto responseDto = new ResponseDto();
        try {
            boolean kq= teamProjectService.save(teamProjectDTO);
            if(kq){
                responseDto.setResponseCode(AppConstants.RESPONSE_OK);
                responseDto.setMessage("thêm issues thành công");
            }
            else {
                responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
                responseDto.setMessage("thêm issues thất bại");
            }
        } catch (Exception e) {
            responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
            responseDto.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @GetMapping("/team-project/get-member-by-projectId/{projectId}")
    public ResponseEntity<ResponseDto> addIssues(@PathVariable(name = "projectId") Long projectId) {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<EmployeeDto> listMember= teamProjectService.getAllIdMemberByProjectId(projectId);
            if(listMember.size()!=0){
                responseDto.setResponseCode(AppConstants.RESPONSE_OK);
                responseDto.setMessage("Lấy dữ liệu thành công");
                responseDto.setDataResponse(listMember);
            }
            else {
                responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
                responseDto.setMessage("lấy thất bại");
            }
        } catch (Exception e) {
            responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
            responseDto.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
