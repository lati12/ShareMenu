package com.server.sharemenu.services;

import com.server.sharemenu.common.Template;
import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.TemplateRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

//Сървис клас който служи за качване и сваляне на файлове от сървъра

@Service
public class FileService {

    @Value("${files.path}")
    private String filesPath;

    private final TemplateRepository templateRepository;

    public FileService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public Template uploadFile(MultipartFile file, User user) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Template template = templateRepository.findTemplateByName(fileName);

        if (template == null) {
            template = new Template();
            template.setUsers(user);
            template.setName(fileName);
        }

        template.setFile(file.getBytes());

        return templateRepository.save(template);
    }

    public Resource download(String fileName) {
        try {
            Path file = Paths.get(fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
