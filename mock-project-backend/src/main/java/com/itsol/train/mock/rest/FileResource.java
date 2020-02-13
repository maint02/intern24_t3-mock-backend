package com.itsol.train.mock.rest;

import com.itsol.train.mock.constants.AppConstants;
import com.itsol.train.mock.dto.ResponseDto;
import com.itsol.train.mock.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/file")
public class FileResource {
    @Autowired
    FileService fileService;
    @PostMapping("/upload")
    public ResponseEntity<ResponseDto> upload(@RequestParam("file") MultipartFile file){
        ResponseDto dto=new ResponseDto();
        boolean resultUpload=fileService.saveFileToSystem(file);
        if (resultUpload){
            dto.setMessage("Upload file thành công!");
            dto.setResponseCode(AppConstants.RESPONSE_OK);
        }else {
            dto.setMessage("Upload file thất bại");
            dto.setResponseCode(AppConstants.RESPONSE_ERRORS);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping("/upload-and-get-file")
    public ResponseEntity<Resource> uploadAndGetFile(@RequestParam("file") MultipartFile file){
       boolean resultUpload=fileService.saveFileToSystem(file);
       Resource resourceOutput;
       if (resultUpload){
           resourceOutput=fileService.getSingleFile(file.getOriginalFilename());
       }
       else {
          resourceOutput=null;
       }
       return ResponseEntity.ok(resourceOutput);
    }
    @GetMapping("/get-file/{nameFile:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFileByName(@PathVariable("nameFile") String nameFile){
       Resource resourceOutput=fileService.getSingleFile(nameFile);
       if (resourceOutput!=null){
           return ResponseEntity.ok(resourceOutput);
       }
       return ResponseEntity.ok(null);
    }
    @DeleteMapping("/delete/{nameFile}")
    public ResponseEntity<ResponseDto> deleteFileByName(@PathVariable("nameFile") String nameFile){
        ResponseDto dto=new ResponseDto();
        boolean kq = fileService.deleteFileInSystem(nameFile);
        if (kq){
            dto.setMessage("xóa file ok");
        }else {
            dto.setMessage("xóa file thất bại");
        }
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
