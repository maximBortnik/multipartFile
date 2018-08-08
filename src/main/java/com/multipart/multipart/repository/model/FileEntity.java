package com.multipart.multipart.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileEntity {

    private Long id;
    private String fileName;
    private long contentLength;
    private String contentType;
}
