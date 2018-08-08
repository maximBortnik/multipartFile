package com.multipart.multipart.controller;

import com.multipart.multipart.controller.model.FileDto;
import com.multipart.multipart.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DownloadFileController {

    @Autowired
    private FileService fileService;

    /*
     * Download Files
     */
    @GetMapping("/downloadFile/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws Exception {
        FileDto fileDto = fileService.loadFileByName(filename);

        if (fileDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String contentType = fileDto.getContentType();

        if("application/pdf".equals(contentType)) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileDto.getFileName() + "\"")
                    .body(new ByteArrayResource(fileDto.getContent()));
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getFileName() + "\"")
                .body(new ByteArrayResource(fileDto.getContent()));
    }
}
