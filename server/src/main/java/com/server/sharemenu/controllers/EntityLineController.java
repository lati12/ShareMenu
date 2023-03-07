package com.server.sharemenu.controllers;

import com.server.sharemenu.common.EntityLine;
import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.EntityLineRepository;
import com.server.sharemenu.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

    import org.springframework.transaction.annotation.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * The class serves to consume endpoints from the Entity Line resource
 */
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
    /**
     * The method serves to produce a record in the database
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> insertEntityLine(@RequestBody EntityLine restaurantEntityLine, Principal principal) {

        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {
            EntityLine newRestaurantEntityLine = entitylineRepository.save(restaurantEntityLine);
            return ResponseEntity.ok(newRestaurantEntityLine);
        }

        return ResponseEntity.ok("Contact admin");
    }

    @GetMapping("get")
    @PreAuthorize("hasRole('ROLE_USER')")
    /**
     * The method serves to consume records as the data is filtered by the User making the request
     */
    public ResponseEntity<?> getEntityLine(@RequestParam Long headerId, Principal principal)
    {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {
            List<EntityLine> restaurantEntityLines = entitylineRepository.findAllByEntityHeaderId(headerId);
            return ResponseEntity.ok(restaurantEntityLines);
        }
        return ResponseEntity.ok(new ArrayList<EntityLine>());
    }

    @GetMapping("getById")
    @PreAuthorize("hasRole('ROLE_USER')")
    /**
     * The method serves to consume a record by ID, with the record filtered by the User making the request
     */
    public ResponseEntity<?> getEntityLineById(@RequestParam Long id, Principal principal)
    {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {
            EntityLine entityLines = entitylineRepository.findEntityLineById(id);
            return ResponseEntity.ok(entityLines);
        }
        return ResponseEntity.ok(new EntityLine());
    }

    @DeleteMapping("delete")
    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    /**
     * The method serves to delete a record from the database
     */
    public ResponseEntity<?> deleteEntityLine(@RequestParam(value = "id") Long id, Principal principal)
    {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {
            entitylineRepository.deleteEntityLineById(id    );
            return ResponseEntity.ok("Record has been deleted");
        }

        return ResponseEntity.ok("Delete record not allowed");
    }
}
