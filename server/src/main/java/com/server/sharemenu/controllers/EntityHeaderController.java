package com.server.sharemenu.controllers;

import com.server.sharemenu.common.EntityHeader;
import com.server.sharemenu.repositories.EntityHeaderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/entityheader")
public class EntityHeaderController {
    private final EntityHeaderRepository entityHeaderRepository;

    public EntityHeaderController(EntityHeaderRepository entityHeaderRepository) {
        this.entityHeaderRepository = entityHeaderRepository;
    }
    @PostMapping("/insert")
    public ResponseEntity<?> insertEntityHeader(@RequestBody EntityHeader entityHeader) {
        EntityHeader newEntityHeader = entityHeaderRepository.save(entityHeader);

        return ResponseEntity.ok(newEntityHeader);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getEntityHeader()
    {
        List<EntityHeader> entityHeaders = entityHeaderRepository.findAll();

        return ResponseEntity.ok(entityHeaders);
    }
    @GetMapping("getById")
    public ResponseEntity<?> getEntityHeaderById(@RequestParam Long id)
    {
        Optional<EntityHeader> entityHeader = entityHeaderRepository.findById(id);

        return ResponseEntity.ok(entityHeader);
    }
    @DeleteMapping("delete")
    public ResponseEntity<?> deleteEntityHeader(@RequestParam(value ="id") Long id)
    {
        entityHeaderRepository.deleteById(id);

        return ResponseEntity.ok("Record has been deleted");
    }
}
