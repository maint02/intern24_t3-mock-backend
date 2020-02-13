package com.itsol.train.mock.service.impl;

import com.itsol.train.mock.constants.AppConstants;
import com.itsol.train.mock.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public boolean saveFileToSystem(MultipartFile file) {
        Path systemFolder= Paths.get(AppConstants.SYSTEM_FOLDER_IMAGES);
        try {
            InputStream inputStream=file.getInputStream();
            Files.copy(inputStream,systemFolder.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Lưu file thành công");
            return true;
        } catch (IOException e) {
            System.out.println("Lưu file thất bại");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Resource getSingleFile(String fileName) {
        Path fileFromFolder=Paths.get(AppConstants.SYSTEM_FOLDER_IMAGES).resolve(fileName);
        try {
            Resource resourceOutput=new UrlResource(fileFromFolder.toUri());
            if (resourceOutput.exists() || resourceOutput.isReadable()) {
                System.out.println("Dữ liệu file lấy ra: "+resourceOutput);
                return resourceOutput;
            }
        } catch (MalformedURLException e) {
            System.out.println("Lỗi không lấy được File ra ");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteFileInSystem(String fileName) {
        Path systemFolder= Paths.get(AppConstants.SYSTEM_FOLDER_IMAGES);
        try {
            Files.delete(systemFolder.resolve(fileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
