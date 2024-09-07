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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Service class that serves to create a menu based on the JaspreReport template provided by the database.
 */
@Service
public class MenuReportService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Value("${sharemenu.path.files_pdf}")
    private String tempPdfFolder;

    @Value("${sharemenu.path.files_png}")
    private String tempPngFolder;

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

        menuLineViewJaspers.add(new MenuLineViewJasper(menuLineViewList.get(0).getItemCategoryName()));
        for (MenuLineView menuLineView : menuLineViewList) {
           /* if(!groupCategory.equals(menuLineView.getItemCategoryName()))
            {
                MenuLineViewJasper categoryLine = new MenuLineViewJasper(menuLineView.getItemCategoryName());
                menuLineViewJaspers.add(categoryLine);
            }
*/
            menuLineViewJaspers.add(new MenuLineViewJasper(menuLineView));
           // groupCategory = menuLineView.getItemCategoryName();
        }

        return menuLineViewJaspers;
    }

    public Resource getResource(){return null;}

    private JasperPrint mapJasperReport(ShareMenu shareMenu, MenuHeaderView menuHeaderView, List<MenuLineViewJasper> menuLineViews, Principal principal) throws JRException{


        Template template = templateRepository.findTemplateByName(shareMenu.getEntityHeader().getTemplate().getName());

        InputStream inputStreamTemplate = new ByteArrayInputStream(template.getFile());

        JasperDesign jasperDesign = JRXmlLoader.load(inputStreamTemplate);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JRBeanCollectionDataSource dataSource = new
                JRBeanCollectionDataSource(menuLineViews, false);
        Map<String , Object> parameters = new HashMap<>();
        parameters.put("ShareMenuParameter", dataSource);

        JasperPrint jasperPrint = JasperFillManager
                .fillReport(jasperReport,parameters, dataSource);//new JREmptyDataSource());

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        String fileKey = principal.getName().replace('@','_');

        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(tempPdfFolder + fileKey +".pdf"));

        SimplePdfReportConfiguration reportConfig =
                new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exporterConfig
                = new SimplePdfExporterConfiguration();
        exporterConfig.setEncrypted(true);

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exporterConfig);

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exporterConfig);

        exporter.exportReport();
        File file = new File(tempPngFolder + fileKey + ".png");
        OutputStream ouputStream = null;
        try {
            ouputStream = new FileOutputStream(file);
            DefaultJasperReportsContext ctx = DefaultJasperReportsContext.getInstance();
            JasperPrintManager printManager = JasperPrintManager.getInstance(ctx);

            BufferedImage[] images = new BufferedImage[(jasperPrint.getPages().size())];

            for(int i = 0; i < jasperPrint.getPages().size(); i++){
                images[i] = (BufferedImage) printManager.printPageToImage(jasperPrint, i, 10f);
            }

            BufferedImage joinedImg = joinBufferedImage(images[0], images[1]);
            ImageIO.write(joinedImg, "png", ouputStream);
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
    public static BufferedImage joinBufferedImage(BufferedImage img1,
                                                  BufferedImage img2) {
        int offset = 5;
        int width = Math.max(img1.getWidth(), img2.getWidth()) + offset;
        int height = img1.getHeight() + img2.getHeight() + offset;

        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setPaint(Color.BLACK);
        g2.fillRect(0, 0, width, height);
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, 0, img1.getHeight() + offset);
        g2.dispose();
        return newImage;
    }
    public void processReport(ShareMenu shareMenu, Principal principal) throws  JRException{
        mapJasperReport(shareMenu, getHeader(shareMenu), getLines(shareMenu), principal);
    }
}
