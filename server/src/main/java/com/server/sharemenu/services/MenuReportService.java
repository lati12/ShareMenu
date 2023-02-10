package com.server.sharemenu.services;

import com.server.sharemenu.common.*;
import com.server.sharemenu.repositories.MenuHeaderViewRepository;
import com.server.sharemenu.repositories.MenuLineViewRepository;
import com.server.sharemenu.repositories.TemplateRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

//Сървис клас който служи за създаване на меню на база JaspreReport шаблон подаден от базата.

@Service
public class MenuReportService {
    private final MenuHeaderViewRepository menuHeaderViewRepository;
    private final MenuLineViewRepository menuLineViewRepository;
    private final TemplateRepository templateRepository;

    public MenuReportService(MenuHeaderViewRepository menuHeaderViewRepository, MenuLineViewRepository menuLineViewRepository, TemplateRepository templateRepository) {
        this.menuHeaderViewRepository = menuHeaderViewRepository;
        this.menuLineViewRepository = menuLineViewRepository;
        this.templateRepository = templateRepository;
    }

    private MenuHeaderViewJasper getHeader(ShareMenu shareMenu){
        MenuHeaderView menuHeaderView = menuHeaderViewRepository.getMenuHeaderView(shareMenu.getEntityHeader().getId());
        MenuHeaderViewJasper menuHeaderViewJasper = new MenuHeaderViewJasper(menuHeaderView);
        return menuHeaderViewJasper;
    }
    private List<MenuLineViewJasper> getLines(ShareMenu shareMenu){
        List<MenuLineView> menuLineViewList = menuLineViewRepository.getMenuLineView(shareMenu.getEntityHeader().getId());
        List<MenuLineViewJasper> menuLineViewJaspers = new ArrayList<>();
        String groupCategory = "";

        for (MenuLineView menuLineView : menuLineViewList) {
            if(!groupCategory.equals(menuLineView.getItemCategoryName()))
            {
                MenuLineViewJasper categoryLine = new MenuLineViewJasper(menuLineView.getItemCategoryName());
                menuLineViewJaspers.add(categoryLine);
            }

            menuLineViewJaspers.add(new MenuLineViewJasper(menuLineView));
            groupCategory = menuLineView.getItemCategoryName();
        }

        return menuLineViewJaspers;
    }

    public Resource getResource(){return null;}

    private JasperPrint mapJasperReport(ShareMenu shareMenu, MenuHeaderView menuHeaderView, List<MenuLineViewJasper> menuLineViews) throws JRException{


        Template template = templateRepository.findTemplateByName(shareMenu.getEntityHeader().getTemplate().getName());

        InputStream inputStreamTemplate = new ByteArrayInputStream(template.getFile());

        JasperDesign jasperDesign = JRXmlLoader.load(inputStreamTemplate);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JRBeanCollectionDataSource dataSource = new
                JRBeanCollectionDataSource(menuLineViews, false);
        Map<String , Object> parameters = new HashMap<>();
        parameters.put("ShareMenuParameter", dataSource);

        JasperPrint jasperPrint = JasperFillManager
                .fillReport(jasperReport,parameters, new JREmptyDataSource());

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("test.pdf"));

        SimplePdfReportConfiguration reportConfig =
                new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exporterConfig
                = new SimplePdfExporterConfiguration();
        exporterConfig.setMetadataAuthor("baeldung");
        exporterConfig.setEncrypted(true);
        exporterConfig.setAllowedPermissionsHint("PRINTING");

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exporterConfig);

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exporterConfig);

        exporter.exportReport();

        File file = new File("image.png");
        OutputStream ouputStream = null;
        try {
            ouputStream = new FileOutputStream(file);
            DefaultJasperReportsContext ctx = DefaultJasperReportsContext.getInstance();
            JasperPrintManager printManager = JasperPrintManager.getInstance(ctx);

            BufferedImage rendered_image = null;
            rendered_image = (BufferedImage) printManager.printPageToImage(jasperPrint, 0, 10f);
            ImageIO.write(rendered_image, "png", ouputStream);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ouputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return jasperPrint;
    }

    public void processReport(ShareMenu shareMenu) throws  JRException{
        mapJasperReport(shareMenu, getHeader(shareMenu), getLines(shareMenu));
    }
}
