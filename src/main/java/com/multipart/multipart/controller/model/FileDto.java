package com.multipart.multipart.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDto {

    private String fileName;
    private long contentLength;
    private String contentType;
    private byte[] content;

}
