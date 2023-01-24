package com.server.sharemenu.controllers;

import com.server.sharemenu.repositories.TemplateRepository;
import com.server.sharemenu.services.JasperReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/jasperreport")
public class JasperReport {
    @Autowired
    private TemplateRepository repository;

    @Autowired
    private JasperReportService service;

    @GetMapping("jasper/{format}")
    public String generatePDF(@PathVariable String format) throws JRException, FileNotFoundException{
        return service.exportReport(format);
    }
}
