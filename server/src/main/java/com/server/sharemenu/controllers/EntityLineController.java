package com.server.sharemenu.controllers;

import com.server.sharemenu.common.EntityLine;
import com.server.sharemenu.common.Item;
import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.EntitylineRepository;
import com.server.sharemenu.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resource/entityline")
public class EntityLineController {

    private final EntitylineRepository entitylineRepository;
    private final UserRepository userRepository;

    public EntityLineController(EntitylineRepository entitylineRepository, UserRepository userRepository) {
        this.entitylineRepository = entitylineRepository;
        this.userRepository = userRepository;
    }
    @PostMapping("/insert")
    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> insertEntityLine(@RequestBody EntityLine entityLine, Principal principal) {

        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            entityLine.setUsers(user.get());
            EntityLine newEntityLine = entitylineRepository.save(entityLine);
            return ResponseEntity.ok(newEntityLine);
        }

        return ResponseEntity.ok("Item not has added. Contact to admin");
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getEntityLine(Principal principal) {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            List<EntityLine> entityLines = entitylineRepository.findEntityLineByUsersId(user.get().getId());
            return ResponseEntity.ok(entityLines);
        }

        return ResponseEntity.ok(new ArrayList<EntityLine>());

    }
    @GetMapping("getById")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getEntityLineById(@RequestParam Long id, Principal principal) {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            EntityLine entityLine = entitylineRepository.findEntityLineByIdAndUsersId(id, user.get().getId());

            return ResponseEntity.ok(entityLine);
        }
        return ResponseEntity.ok(new EntityLine());

    }
    @DeleteMapping("delete")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public ResponseEntity<?> deleteEntityLine(@RequestParam(value = "id") Long id, Principal principal) {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            entitylineRepository.deleteEntityLineByIdAndUsersId(id, user.get().getId());
            return ResponseEntity.ok("Record has been deleted");
        }

        return ResponseEntity.ok("Delete record not allowed");
    }

}
