package com.server.sharemenu.controllers;

import com.server.sharemenu.common.EntityLine;
import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.EntityLineRepository;
import com.server.sharemenu.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resource/entityline")
public class EntityLineController {

    private final EntityLineRepository entitylineRepository;
    private final UserRepository userRepository;

    public EntityLineController(EntityLineRepository entitylineRepository, UserRepository userRepository) {
        this.entitylineRepository = entitylineRepository;
        this.userRepository = userRepository;
    }
    @PostMapping("insert")
    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> insertEntityLine(@RequestBody EntityLine restaurantEntityLine) {
        EntityLine newRestaurantEntityLine = entitylineRepository.save(restaurantEntityLine);

        return ResponseEntity.ok(newRestaurantEntityLine);
    }

    @GetMapping("get")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getEntityLine(@RequestParam Long headerId)
    {
        List<EntityLine> restaurantEntityLines = entitylineRepository.findAllByEntityHeaderId(headerId);

        return ResponseEntity.ok(restaurantEntityLines);
    }
    @GetMapping("getById")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getEntityLineById(@RequestParam Long id)
    {
        Optional<EntityLine> entityLines = entitylineRepository.findById(id);

        return ResponseEntity.ok(entityLines);
    }
    @DeleteMapping("delete")
    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteEntityLine(@RequestParam(value = "id") Long id)
    {
        entitylineRepository.deleteById(id);

        return ResponseEntity.ok("Record has been deleted");
    }
}
