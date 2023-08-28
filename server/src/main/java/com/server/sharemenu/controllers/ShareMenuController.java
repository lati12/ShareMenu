package com.server.sharemenu.controllers;

import com.server.sharemenu.common.ShareMenu;
import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.ShareMenuRepository;
import com.server.sharemenu.repositories.UserRepository;
import com.server.sharemenu.response.facebook.FacebookPostResponse;
import com.server.sharemenu.services.FileService;
import com.server.sharemenu.services.MenuReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;

import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The class serves to consume end-points from the ShareMenu resource
 * and then for the individual methods
 */

@RestController
@RequestMapping("/api/resource/sharemenu")
public class ShareMenuController {

    @Value("${sharemenu.path.files_pdf}")
    private String tempPdfFolder;

    @Value("${sharemenu.path.files_png}")
    private String tempPngFolder;

    private final ShareMenuRepository shareMenuRepository;
    private final UserRepository userRepository;
    private final MenuReportService menuReportService;
    private final FileService fileService;
    private final RestTemplate restTemplate;

    public ShareMenuController(ShareMenuRepository shareMenuRepository, MenuReportService menuReportService, FileService fileService, UserRepository userRepository
            , RestTemplateBuilder restTemplateBuilder) {
        this.shareMenuRepository = shareMenuRepository;
        this.menuReportService = menuReportService;
        this.fileService = fileService;
        this.userRepository = userRepository;
        this.restTemplate = restTemplateBuilder.build();
    }
    @PostMapping("/insert")
    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    /**
     * The method serves to produce a record in the database
     */
    public ResponseEntity<?> InsertShareMenu(@RequestBody ShareMenu shareMenu, Principal principal){

        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            shareMenu.setUsers(user.get());
            ShareMenu newShareMenu = shareMenuRepository.save(shareMenu);
            return ResponseEntity.ok(newShareMenu);
        }
        return ResponseEntity.ok("Record not allowed to save.");

    }
    @GetMapping("/get")
    @PreAuthorize("hasRole('ROLE_USER')")
    /**
     * The method serves to consume records as the data is filtered by the User making the request
     */
    public ResponseEntity<?> getShareMenu(Principal principal){
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            List<ShareMenu> shareMenus = shareMenuRepository.findShareMenuByUsersId(user.get().getId());
            return ResponseEntity.ok(shareMenus);
        }

        return ResponseEntity.ok(new ArrayList<ShareMenu>());

    }
    @GetMapping("/getById")
    @PreAuthorize("hasRole('ROLE_USER')")
    /**
     * The method serves to consume records as the data is filtered by the User making the request
     */
    public ResponseEntity<?> getShareMenuById(@RequestParam Long id, Principal principal){
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            ShareMenu shareMenu = shareMenuRepository.findShareMenuByIdAndUsersId(id, user.get().getId());
            return ResponseEntity.ok(shareMenu);
        }

        return ResponseEntity.ok("Read record not allowed for user");
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    /**
     *  The method serves to delete a record from the database
     */
    public ResponseEntity<?> DeleteShareMenu(@PathVariable Long id, Principal principal){
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()){
            shareMenuRepository.deleteShareMenuByIdAndUsersId(id, user.get().getId());
            return ResponseEntity.ok("Record has been deleted");
        }

        return ResponseEntity.ok("Delete record not allowed");
    }

    @PostMapping("/generate-file")
    /**
     * The method serves to generate data
     */
    public ResponseEntity<?> generateFile(@RequestBody ShareMenu shareMenu, Principal principal) throws JRException, IOException {
            menuReportService.processReport(shareMenu, principal);
        String fileKey = principal.getName().replace('@','_');

        Resource file = fileService.download(tempPdfFolder + fileKey + ".pdf");
        Path path = file.getFile().toPath();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
    @PostMapping("share-menu")
    /**
     * The method serves to share data
     */
    public ResponseEntity<?> shareMenu(@RequestBody ShareMenu shareMenu, Principal principal) throws JRException {
        String url = "https://graph.facebook.com/"+shareMenu.getSocialNetworkConnectivity().getKey()+"/photos?";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String fileKey = principal.getName().replace('@','_');

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();

        File file = new File(tempPngFolder +fileKey + ".png");
        if(!file.exists()) {
            menuReportService.processReport(shareMenu, principal);
        }

        body.add("source", new FileSystemResource(file));
        body.add("access_token", shareMenu.getSocialNetworkConnectivity().getAccessToken());
        body.add("published", true);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
        this.restTemplate.postForEntity(url, entity, FacebookPostResponse.class);
        return ResponseEntity.ok("Done");
    }
}