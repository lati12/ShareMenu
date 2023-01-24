package com.server.sharemenu.controllers;

import com.server.sharemenu.common.ShareMenu;
import com.server.sharemenu.repositories.ShareMenuRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/sharemenu")
public class ShareMenuController {
    private final ShareMenuRepository shareMenuRepository;

    public ShareMenuController(ShareMenuRepository shareMenuRepository) {
        this.shareMenuRepository = shareMenuRepository;
    }
    @PostMapping("insert")
    public ResponseEntity<?> InsertShareMenu(@RequestBody ShareMenu shareMenu){
        ShareMenu newShareMenu = shareMenuRepository.save(shareMenu);

        return ResponseEntity.ok(newShareMenu);
    }
    @GetMapping("get")
    public ResponseEntity<?> getShareMenu(){
        List<ShareMenu> shareMenus = shareMenuRepository.findAll();

        return ResponseEntity.ok(shareMenus);
    }
    @GetMapping("getById")
    public ResponseEntity<?> getShareMenuById(@RequestParam Long id){
        Optional<ShareMenu> shareMenu = shareMenuRepository.findById(id);

        return ResponseEntity.ok(shareMenu);
    }
    @DeleteMapping("delete")
    public ResponseEntity<?> DeleteShareMenu(@PathVariable Long id){
        shareMenuRepository.deleteById(id);

        return ResponseEntity.ok("Record has been deleted");
    }
}