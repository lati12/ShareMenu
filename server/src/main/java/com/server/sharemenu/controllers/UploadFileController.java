package com.server.sharemenu.controllers;

import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.UserRepository;
import com.server.sharemenu.services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

/*
Класът служи за консумиране на end-poinds от ресурса UploadFile и после за отделните методи.
 */

@CrossOrigin(origins = {"http://sharemenu.eu", "http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/api/resource/upload-file")
public class UploadFileController {

    FileService fileService;
    private final UserRepository userRepository;

    public UploadFileController(FileService fileService, UserRepository userRepository) {
        this.fileService = fileService;
        this.userRepository = userRepository;
    }
    // Методът служи за качване на данни
    @PostMapping(value = "upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file, Principal principal) throws IOException {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());
        if (user.isPresent()) {
            fileService.uploadFile(file, user.get());
        }

        return ResponseEntity.ok("OK");
    }

}
