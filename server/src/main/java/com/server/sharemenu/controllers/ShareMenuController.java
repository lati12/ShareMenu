package com.server.sharemenu.controllers;

import com.server.sharemenu.common.ShareMenu;
import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.ShareMenuRepository;
import com.server.sharemenu.repositories.UserRepository;
import com.server.sharemenu.services.FileService;
import com.server.sharemenu.services.MenuReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.Resource;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resource/sharemenu")
public class ShareMenuController {
    private final ShareMenuRepository shareMenuRepository;
    private final UserRepository userRepository;
    private final MenuReportService menuReportService;
    private final FileService fileService;

    public ShareMenuController(ShareMenuRepository shareMenuRepository, MenuReportService menuReportService, FileService fileService, UserRepository userRepository) {
        this.shareMenuRepository = shareMenuRepository;
        this.menuReportService = menuReportService;
        this.fileService = fileService;
        this.userRepository = userRepository;
    }

    @PostMapping("/insert")
    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
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
    public ResponseEntity<?> DeleteShareMenu(@PathVariable Long id, Principal principal){
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()){
            shareMenuRepository.deleteShareMenuByIdAndUsersId(id, user.get().getId());
            return ResponseEntity.ok("Record has been deleted");
        }

        return ResponseEntity.ok("Delete record not allowed");
    }

    @PostMapping("/generate-file")
    public ResponseEntity<?> generateFile(@RequestBody ShareMenu shareMenu) throws JRException, IOException {
        menuReportService.processReport(shareMenu);
        Resource file = fileService.download("test.pdf");
        Path path = file.getFile().toPath();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}