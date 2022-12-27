package com.server.sharemenu.services;

import com.server.sharemenu.models.Template;
import com.server.sharemenu.repositories.TemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UploadFileService {

    private final TemplateRepository templateRepository;

    public UploadFileService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public Template uploadFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Template template = new Template();
        template.setName(fileName);
        template.setFile(file.getBytes());

        return templateRepository.save(template);
    }
}
