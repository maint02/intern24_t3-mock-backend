package com.itsol.train.mock.rest;

import com.itsol.train.mock.dto.ResponseDto;
import com.itsol.train.mock.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api")
public class UploadFileResource {
    @Autowired
    UploadFileService uploadFileService;
    @PostMapping("/upload")
    public ResponseEntity<ResponseDto> upload(@RequestParam("file") MultipartFile file){
        ResponseDto dto=new ResponseDto();
        boolean resultUpload=uploadFileService.saveFileToSystem(file);
        if (resultUpload){
            dto.setMessage("Upload file thành công!");
        }else {
            dto.setMessage("Upload file thất bại");
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping("/upload-and-get-file")
    public ResponseEntity<Resource> uploadAndGetFile(@RequestParam("file") MultipartFile file){
       boolean resultUpload=uploadFileService.saveFileToSystem(file);
       Resource resourceOutput;
       if (resultUpload){
           resourceOutput=uploadFileService.getSingleFile(file.getOriginalFilename());
       }
       else {
          resourceOutput=null;
       }
       return ResponseEntity.ok(resourceOutput);
    }
    @GetMapping("/get-file/{nameFile}")
    @ResponseBody
    public ResponseEntity<Resource> getFileByName(@PathVariable("nameFile") String nameFile){
       Resource resourceOutput=uploadFileService.getSingleFile(nameFile);
       if (resourceOutput!=null){
           return ResponseEntity.ok(resourceOutput);
       }
       return ResponseEntity.ok(null);
    }
}
