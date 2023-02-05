package com.server.sharemenu.services;

import com.server.sharemenu.common.Template;
import com.server.sharemenu.repositories.TemplateRepository;
import org.apache.commons.lang3.text.translate.NumericEntityUnescaper;
import org.aspectj.apache.bcel.classfile.Module;
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

        Template template = templateRepository.findTemplateByName(fileName);

        if (template == null) {
            template = new Template();
            template.setName(fileName);
        }

        template.setFile(file.getBytes());

        return templateRepository.save(template);
    }
}
