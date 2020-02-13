package com.itsol.train.mock.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
 boolean saveFileToSystem(MultipartFile file);
 Resource getSingleFile(String fileName);
}
