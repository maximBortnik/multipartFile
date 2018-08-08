package com.multipart.multipart.service.impl;

import com.multipart.multipart.controller.model.FileDto;
import com.multipart.multipart.exception.MyFileNotFoundException;
import com.multipart.multipart.repository.FileRepository;
import com.multipart.multipart.repository.model.FileEntity;
import com.multipart.multipart.service.FileService;
import com.multipart.multipart.service.FileStorage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private static final long MAX_FILE_SIZE = 50000000L;
    private static final long EMPTY_FILE_SIZE = 0;

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileStorage fileStorage;


    @Override
    public FileEntity save(MultipartFile multipartFile) {
        validateFile(multipartFile);
        fileStorage.storeFile(multipartFile);
        return fileRepository.save(FileEntity.builder()
                .fileName(multipartFile.getName())
                .contentType(multipartFile.getContentType())
                .contentLength(multipartFile.getSize())
                .build());
    }

    @Override
    public List<FileEntity> save(MultipartFile[] multipartFiles) {
        return Arrays.stream(multipartFiles)
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public FileDto loadFileByName(String filename) throws Exception{

        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be empty");
        }

        Resource resource = fileStorage.loadFile(filename);
        FileEntity fileEntity = fileRepository.getByName(filename);

        if (fileEntity == null) {
            throw new MyFileNotFoundException("File not found " + filename);

        }
        return FileDto.builder()
                .fileName(fileEntity.getFileName())
                .contentType(fileEntity.getContentType())
                .contentLength(fileEntity.getContentLength())
                .content(IOUtils.toByteArray(resource.getInputStream()))
                .build();
    }

    private void validateFile(MultipartFile file) {
        if (file == null || file.getSize() == EMPTY_FILE_SIZE) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("File cannot exceed 50MB");
        }
    }
}
