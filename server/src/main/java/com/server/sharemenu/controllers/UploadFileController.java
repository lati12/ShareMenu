package com.server.sharemenu.controllers;

import com.server.sharemenu.models.Item;
import com.server.sharemenu.repositories.ItemRepository;
import com.server.sharemenu.services.UploadFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
