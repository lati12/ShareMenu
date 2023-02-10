package com.server.sharemenu.controllers;

import com.server.sharemenu.common.EntityHeader;
import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.EntityHeaderRepository;
import com.server.sharemenu.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/*
Класът служи за консумиране на end-poinds от ресурса EntityHeader
и после за отделните методи
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resource/entityheader")
public class EntityHeaderController {
    private final EntityHeaderRepository entityHeaderRepository;
    private final UserRepository userRepository;

    public EntityHeaderController(EntityHeaderRepository entityHeaderRepository, UserRepository userRepository) {
        this.entityHeaderRepository = entityHeaderRepository;
        this.userRepository = userRepository;
    }

    //Методът служи за продуциране на запис в базата данни
    @PostMapping("/insert")
    public ResponseEntity<?> insertEntityHeader(@RequestBody EntityHeader entityHeader, Principal principal) {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {
            entityHeader.setUsers(user.get());
            EntityHeader newEntityHeader = entityHeaderRepository.save(entityHeader);
            return ResponseEntity.ok(newEntityHeader);
        }

        return ResponseEntity.ok("Contact admin");

    }
    // Методът служи за консумиране на записи като данните са филтрирани по User, който прави заявката
    @GetMapping("/get")
    public ResponseEntity<?> getEntityHeader()
    {
        List<EntityHeader> entityHeaders = entityHeaderRepository.findAll();

        return ResponseEntity.ok(entityHeaders);
    }
    //Методът служи за консумиране на запис по ID като записът е филтриран по User, който прави заявката
    @GetMapping("getById")
    public ResponseEntity<?> getEntityHeaderById(@RequestParam Long id)
    {
        Optional<EntityHeader> entityHeader = entityHeaderRepository.findById(id);

        return ResponseEntity.ok(entityHeader);
    }
    // Методът служи за изтриване на запис от базата данни
    @DeleteMapping("delete")
    public ResponseEntity<?> deleteEntityHeader(@RequestParam(value ="id") Long id)
    {
        entityHeaderRepository.deleteById(id);

        return ResponseEntity.ok("Record has been deleted");
    }
}
