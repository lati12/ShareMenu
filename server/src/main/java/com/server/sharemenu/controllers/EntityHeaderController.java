package com.server.sharemenu.controllers;

import com.server.sharemenu.common.EntityHeader;
import com.server.sharemenu.common.Item;
import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.EntityHeaderRepository;
import com.server.sharemenu.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The class serves to consume endpoints from the Entity Header resource
 * and then for the individual methods
 */

@RestController
@RequestMapping("/api/resource/entityheader")
public class EntityHeaderController {
    private final EntityHeaderRepository entityHeaderRepository;
    private final UserRepository userRepository;

    public EntityHeaderController(EntityHeaderRepository entityHeaderRepository, UserRepository userRepository) {
        this.entityHeaderRepository = entityHeaderRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/insert")
    /**
     * The method serves to produce a record in the database
     */
    public ResponseEntity<?> insertEntityHeader(@RequestBody EntityHeader entityHeader, Principal principal) {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {
            entityHeader.setUsers(user.get());
            EntityHeader newEntityHeader = entityHeaderRepository.save(entityHeader);
            return ResponseEntity.ok(newEntityHeader);
        }

        return ResponseEntity.ok("Contact admin");

    }
    @GetMapping("/get")
    /**
     * The method serves to consume records as the data is filtered by the User making the request
     */
    public ResponseEntity<?> getEntityHeader(Principal principal)
    {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {
            List<EntityHeader> entityHeaders = entityHeaderRepository.findEntityHeadersByUsersId(user.get().getId());
            return ResponseEntity.ok(entityHeaders);
        }
        return ResponseEntity.ok(new ArrayList<EntityHeader>());
    }
    @GetMapping("getById")
    /**
     * The method serves to consume a record by ID, with the record filtered by the User making the request
     */
    public ResponseEntity<?> getEntityHeaderById(@RequestParam Long id, Principal principal)
    {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {
            EntityHeader entityHeader = entityHeaderRepository.findEntityHeaderByIdAndUsersId(id, user.get().getId());
            return ResponseEntity.ok(entityHeader);
        }
        return ResponseEntity.ok(new EntityHeader());
    }

    @DeleteMapping("delete")
    /**
     * The method serves to delete a record from the database
     */
    public ResponseEntity<?> deleteEntityHeader(@RequestParam(value ="id") Long id, Principal principal)
    {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {
            entityHeaderRepository.deleteEntityHeaderByIdAndUsersId(id, user.get().getId());
            return ResponseEntity.ok("Record has been deleted");
        }

        return ResponseEntity.ok("Delete record not allowed");
    }
}
