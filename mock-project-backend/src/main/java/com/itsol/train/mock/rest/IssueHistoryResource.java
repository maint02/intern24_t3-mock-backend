package com.itsol.train.mock.rest;

import com.itsol.train.mock.constants.AppConstants;
import com.itsol.train.mock.dto.IssueDTO;
import com.itsol.train.mock.dto.IssueHistoryDTO;
import com.itsol.train.mock.dto.ResponseDto;
import com.itsol.train.mock.service.IssueHistoryService;
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
public class IssueHistoryResource {
    @Autowired
    IssueHistoryService issueHistoryService;
    @GetMapping("/issue-history/{issueId}")
    public ResponseEntity<ResponseDto> getIssues(@PathVariable(name = "issueId") Long id) {
        ResponseDto responseDto = new ResponseDto();
        List<IssueHistoryDTO> dtos =issueHistoryService.getAllByIssueId(id);
        if (dtos!=null){
            responseDto.setMessage("Get Issues Success");
            responseDto.setDataResponse(dtos);
            responseDto.setResponseCode(AppConstants.RESPONSE_OK);
        }else {
            responseDto.setMessage("Get Issues Error");
            responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
        }
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.CREATED);
    }
}
