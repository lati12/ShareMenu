package com.server.sharemenu.controllers;

import com.server.sharemenu.common.Template;
import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.TemplateRepository;
import com.server.sharemenu.repositories.UserRepository;
import com.server.sharemenu.services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resource/template")
public class TemplateController {
    private final TemplateRepository templateRepository;
    FileService fileService;
    private final UserRepository userRepository;

    public TemplateController(TemplateRepository templateRepository, FileService fileService, UserRepository userRepository) {
        this.templateRepository = templateRepository;
        this.fileService = fileService;
        this.userRepository = userRepository;
    }
    @PostMapping("insert")
    public ResponseEntity<?> insertTemplate(@RequestBody Template template)
    {
        Template newTemplate = templateRepository.save(template);

        return ResponseEntity.ok(newTemplate);

    }
    @GetMapping("get")
    public  ResponseEntity<?> getTemplate()
    {
        List<Template> newTemplate = templateRepository.findAll();

        return ResponseEntity.ok(newTemplate);
    }
    @GetMapping("getById")
    public ResponseEntity<?> getTemplateById(@RequestParam Long id)
    {
        Optional<Template> template = templateRepository.findById(id);

        return ResponseEntity.ok(template);
    }
    @DeleteMapping ("delete")
    public ResponseEntity<?> deleteTemplate(@RequestParam("id") Long id)
    {
        templateRepository.deleteById(id);

        return ResponseEntity.ok("Record was deleted");
    }
    @PostMapping(value = "upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, Principal principal) throws IOException {

        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());
        if (user.isPresent()) {
            fileService.uploadFile(file, user.get());
        }


        return ResponseEntity.ok("OK");
    }

}