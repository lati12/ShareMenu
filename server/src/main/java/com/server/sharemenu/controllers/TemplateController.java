package com.server.sharemenu.controllers;

import com.server.sharemenu.common.Template;
import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.TemplateRepository;
import com.server.sharemenu.repositories.UserRepository;
import com.server.sharemenu.response.UserTemplate;
import com.server.sharemenu.services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

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
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> insertTemplate(@RequestBody Template template) {
        Template newTemplate = templateRepository.save(template);

        return ResponseEntity.ok(newTemplate);

    }

    @GetMapping("get")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getTemplate() {
        List<Template> newTemplate = templateRepository.findAll();

        return ResponseEntity.ok(newTemplate);
    }

    @GetMapping("getByUser")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getTemplateByUser(Principal principal) {
        Optional<User> userOptional = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get().getTemplates());
        }

        return ResponseEntity.ok(new ArrayList<>());
    }


    @GetMapping("getById")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getTemplateById(@RequestParam Long id) {
        Optional<Template> template = templateRepository.findById(id);

        return ResponseEntity.ok(template);
    }

    @DeleteMapping("delete")
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteTemplate(@RequestParam("id") Long id) {
        templateRepository.deleteById(id);

        return ResponseEntity.ok("Record was deleted");
    }

    @PostMapping(value = "upload")
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, Principal principal) throws IOException {

        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());
        if (user.isPresent()) {
            fileService.uploadFile(file, user.get());
        }


        return ResponseEntity.ok("OK");
    }


    @PostMapping("insertUserTemplate")
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> insertTemplate(@RequestBody UserTemplate userTemplate) {
        Optional<User> userOptional = userRepository.findById(userTemplate.getUser().getId());

        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.getTemplates().add(userTemplate.getTemplate());
            userRepository.save(user);
        }

        return ResponseEntity.ok("done");
    }

    @GetMapping("getUserTemplate")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getTemplates() {
        //get all from user_template table
        //get list from userRepository
        //get Set of template in user
        List<User> users = userRepository.findAll();
        List<UserTemplate> userTemplateRespons = new ArrayList<>();

        for (User user : users) {
            Set<Template> templateSet = user.getTemplates();
            for (Template temp : templateSet) {
                UserTemplate userTemplate = new UserTemplate();
                userTemplate.setUser(user);
                userTemplate.setTemplate(temp);
                userTemplateRespons.add(userTemplate);
            }
        }

        return ResponseEntity.ok(userTemplateRespons);
    }

    @DeleteMapping("deleteUserTemplate")
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUserTemplate(@RequestParam("user_id") Long userId, @RequestParam("template_id") Long templateId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isPresent()) {
            User user = userOptional.get();

            Set<Template> templates = new HashSet<>();
            for (Template template : user.getTemplates()) {
                if (!template.getId().equals(templateId)) {
                    templates.add(template);
                }
            }
            user.setTemplates(templates);
            userRepository.save(user);
        }

        return ResponseEntity.ok("Record was deleted");
    }
}