package com.itsol.train.mock.rest;

import com.itsol.train.mock.constants.AppConstants;
import com.itsol.train.mock.constants.IssuesExelUploadConstants;
import com.itsol.train.mock.constants.UpdateIssueConstants;
import com.itsol.train.mock.dto.*;
import com.itsol.train.mock.service.EmployeeIssueService;
import com.itsol.train.mock.service.IssueService;
import com.itsol.train.mock.utils.ExelUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Convert;
import javax.persistence.Converter;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class IssueResource {
    @Autowired
    IssueService issueService;
    @Autowired
    EmployeeIssueService employeeIssueService;

    @PostMapping("/issue")
    public ResponseEntity<ResponseDto> addIssues(@RequestBody IssueDTO issuesDTO) {
        ResponseDto responseDto = new ResponseDto();
        try {
            IssueDTO dto = issueService.save(issuesDTO);
            if (dto != null) {
                responseDto.setResponseCode(AppConstants.RESPONSE_OK);
                responseDto.setMessage("thêm issues thành công");
            } else {
                responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
                responseDto.setMessage("thêm issues thất bại");
            }
        } catch (Exception e) {
            responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
            responseDto.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/issue/{id}")
    public ResponseEntity<ResponseDto> getIssues(@PathVariable(name = "id") Long id) {
        ResponseDto responseDto = new ResponseDto();
        IssueDTO dto = issueService.getById(id);
        if (dto != null) {
            responseDto.setMessage("Get Issues Success");
            responseDto.setDataResponse(dto);
            responseDto.setResponseCode(AppConstants.RESPONSE_OK);
        } else {
            responseDto.setMessage("Get Issues Error");
            responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
        }
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/issue")
    public ResponseEntity<ResponseDto> updateIssues(@RequestBody UpdateIssueDTO updateIssueDTO) {
        ResponseDto responseDto = new ResponseDto();
        if (updateIssueDTO.getId() != null) {
            String kqUpdate = issueService.update(updateIssueDTO);
            if (kqUpdate.equals(UpdateIssueConstants.UPDATE_OK)) {
                responseDto.setMessage("Updated Issues Succcess ");
                responseDto.setResponseCode(AppConstants.RESPONSE_OK);
            } else {
                responseDto.setMessage("Update Issues False ");
                responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
            }
        } else {
            responseDto.setMessage("Not have Id Issues For Update");
        }
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/issue")
    public ResponseEntity<ResponseDto> deleteIssues(@RequestBody Long[] ids) {
        ResponseDto responseDto = new ResponseDto();
        Boolean ResultDelete = issueService.deleteByIds(ids);
        if (ResultDelete == true) {
            responseDto.setMessage("Delete Success");
            responseDto.setResponseCode(AppConstants.RESPONSE_OK);
        } else {
            responseDto.setMessage("Delete Error");
            responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
        }
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/issue/get-by-project/{id}")
    public ResponseEntity<ResponseDto> getIssuesByProjectId(@PathVariable(name = "id") Long id,
                                                            @RequestParam("page") int page,
                                                            @RequestParam("limit") int limit) {
        ResponseDto responseDto = new ResponseDto();
        Pageable pageable = PageRequest.of(page - 1, limit);
        PagingDataDTO pagingDataDTO = issueService.getByProjectIdPaging(id, pageable);
        if (pagingDataDTO != null && pagingDataDTO.getListData().size() != 0) {
            responseDto.setMessage("Get Issues Success");
            responseDto.setDataResponse(pagingDataDTO);
            responseDto.setResponseCode(AppConstants.RESPONSE_OK);
        } else if (pagingDataDTO != null && pagingDataDTO.getListData().size() == 0) {
            responseDto.setMessage("Not Data Issues Response");
            responseDto.setDataResponse(pagingDataDTO);
            responseDto.setResponseCode(AppConstants.RESPONSE_OK);
        } else {
            responseDto.setMessage("Get Issues Error");
            responseDto.setDataResponse(pagingDataDTO);
            responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
        }
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/issue/assign")
    public ResponseEntity<ResponseDto> addTimesheet(@RequestBody EmployeeIssueDTO employeeIssueDTO) {
        ResponseDto responseDto = new ResponseDto();
        boolean kqAdd = employeeIssueService.save(employeeIssueDTO);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/issue/get-by-project/{id}/search")
    public ResponseEntity<ResponseDto> searchIssue(@PathVariable(name = "id") Long id,
                                                   @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                   @RequestParam(value = "limit", required = false, defaultValue = "5") int limit,
                                                   @RequestBody IssueSearchDTO issueSearchDTO
    ) {
        issueSearchDTO.setProjectId(id);
        //--------------------------
        ResponseDto responseDto = new ResponseDto();
        PagingDataDTO pagingDataDTOInput = new PagingDataDTO();
        pagingDataDTOInput.setPage(page - 1);
        pagingDataDTOInput.setLimit(limit);
        //----------------------------
        PagingDataDTO pagingDataDTOOutput = issueService.getByParams(pagingDataDTOInput, issueSearchDTO);
        if (pagingDataDTOOutput.getListData().size() != 0) {
            responseDto.setMessage("có dữ liệu");
            responseDto.setDataResponse(pagingDataDTOOutput);
            responseDto.setResponseCode(AppConstants.RESPONSE_OK);
        } else {
            responseDto.setMessage("không có dữ liệu");
            responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/issue/add-by-exel")
    public ResponseEntity<ResponseDto> addIssues(@RequestParam("file") MultipartFile file) {
        ResponseDto responseDto = new ResponseDto();
        Integer kqSaveExel=issueService.saveAllFromExel(file);
        if (kqSaveExel== IssuesExelUploadConstants.UPLOAD_OK){
            responseDto.setMessage("thêm lưu dữ liệu exel thành công");
            responseDto.setResponseCode(AppConstants.RESPONSE_OK);
        }else if(kqSaveExel==IssuesExelUploadConstants.UPLOAD_ERRO){
            responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
            responseDto.setMessage("lưu thất bại ");
        }
        else if(kqSaveExel==IssuesExelUploadConstants.TEMPLATE_EXEL_ERRO){
            responseDto.setResponseCode(AppConstants.RESPONSE_ERRORS);
            responseDto.setMessage("không đúng định dạng exel yêu cầu ");
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @GetMapping("issue/create-exel-and-dowload")
    public ResponseEntity<ByteArrayOutputStream> createAndDowload(){

        return ResponseEntity.ok(null);
    }
}
