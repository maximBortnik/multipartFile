package com.multipart.multipart.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorage {

    MultipartFile storeFile(MultipartFile file);

    Resource loadFile(String filename);

    void deleteAll() throws IOException;
}
