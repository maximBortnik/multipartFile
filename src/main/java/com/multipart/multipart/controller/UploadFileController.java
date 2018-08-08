package com.multipart.multipart.controller;

import com.multipart.multipart.controller.model.FileDto;
import com.multipart.multipart.repository.model.FileEntity;
import com.multipart.multipart.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UploadFileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadFile")
    public ResponseEntity<FileDto> uploadMultipartFile(@RequestParam("file") MultipartFile file) {
        FileEntity fileEntity = fileService.save(file);
        if (fileEntity != null) {
            return new ResponseEntity<>(FileDto.builder()
                    .fileName(file.getName())
                    .build(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/uploadFiles")
    public ResponseEntity<List<FileDto>> uploadMultipartFiles(@RequestParam("files") MultipartFile[] file) {
        List<FileEntity> fileEntity = fileService.save(file);
        if (!CollectionUtils.isEmpty(fileEntity)) {
            List<FileDto> fileDtos = fileEntity.stream()
                    .map(fe -> FileDto.builder().fileName(fe.getFileName()).build())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(fileDtos, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
