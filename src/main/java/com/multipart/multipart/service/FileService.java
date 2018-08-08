package com.multipart.multipart.service;

import com.multipart.multipart.controller.model.FileDto;
import com.multipart.multipart.repository.model.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    FileEntity save(MultipartFile multipartFile);

    List<FileEntity> save(MultipartFile[] multipartFile);

    FileDto loadFileByName(String filename) throws Exception;

}
