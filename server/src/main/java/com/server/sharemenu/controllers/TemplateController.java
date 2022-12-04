package com.server.sharemenu.controllers;

import com.server.sharemenu.models.Template;
import com.server.sharemenu.repositories.TemplateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/template")
public class TemplateController {
    private final TemplateRepository templateRepository;

    public TemplateController(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
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
}