package com.multipart.multipart.repository.impl;

import com.multipart.multipart.repository.FileRepository;
import com.multipart.multipart.repository.model.FileEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class FileRepositoryImpl implements FileRepository{

    @Override
    public FileEntity save(FileEntity fileDto) {
        log.debug("save file into db, file: {}", fileDto);
        return fileDto;
    }

    @Override
    public FileEntity getByName(String fileName) {
        log.debug("retrive file from db, file name: {}", fileName);
        return new FileEntity();
    }
}
