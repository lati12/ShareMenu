package com.server.sharemenu.services;

import com.server.sharemenu.common.Template;
import com.server.sharemenu.repositories.TemplateRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class JasperReportService {

    @Autowired
    private TemplateRepository repository;

    public String exportReport(String reportFormat) throws JRException, FileNotFoundException{
        List<Template> template = repository.findAll();
        String path = "C:\\temp\\Report";

        File file = ResourceUtils.getFile("classpath:templates.jrxml");
        net.sf.jasperreports.engine.JasperReport jasperReport  = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(template);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JRDataSource dataSource1;
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\templates.pdf");
        }
        return "report generated" + path;
    }
}
