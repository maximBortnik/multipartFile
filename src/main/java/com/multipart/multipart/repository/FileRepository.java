package com.multipart.multipart.repository;

import com.multipart.multipart.repository.model.FileEntity;

public interface FileRepository {

    FileEntity save(FileEntity fileDto);

    FileEntity getByName(String fileName);
}
