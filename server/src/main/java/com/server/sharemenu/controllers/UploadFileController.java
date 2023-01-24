package com.server.sharemenu.controllers;

import com.server.sharemenu.services.UploadFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/upload-file")
public class UploadFileController {

    UploadFileService uploadFileService;

    public UploadFileController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @PostMapping(value = "upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file) throws IOException {

        uploadFileService.uploadFile(file);

        return ResponseEntity.ok("OK");
    }

}
