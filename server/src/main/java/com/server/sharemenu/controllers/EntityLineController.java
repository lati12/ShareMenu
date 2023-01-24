package com.server.sharemenu.controllers;

import com.server.sharemenu.common.EntityLine;
import com.server.sharemenu.repositories.EntitylineRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/entityline/")
public class EntityLineController {

    private final EntitylineRepository entitylineRepository;

    public EntityLineController(EntitylineRepository entitylineRepository) {
        this.entitylineRepository = entitylineRepository;
    }
    @PostMapping("insert")
    public ResponseEntity<?> insertEntityLine(@RequestBody EntityLine restaurantEntityLine) {
        EntityLine newRestaurantEntityLine = entitylineRepository.save(restaurantEntityLine);

        return ResponseEntity.ok(newRestaurantEntityLine);
    }

    @GetMapping("get")
    public ResponseEntity<?> getEntityLine(@RequestParam Long headerId)
    {
        List<EntityLine> restaurantEntityLines = entitylineRepository.findAllByEntityHeaderId(headerId);

        return ResponseEntity.ok(restaurantEntityLines);
    }
    @GetMapping("getById")
    public ResponseEntity<?> getEntityLineById(@RequestParam Long id)
    {
        Optional<EntityLine> entityLines = entitylineRepository.findById(id);

        return ResponseEntity.ok(entityLines);
    }
    @DeleteMapping("delete")
    public ResponseEntity<?> deleteEntityLine(@RequestParam(value = "id") Long id)
    {
        entitylineRepository.deleteById(id);

        return ResponseEntity.ok("Record has been deleted");
    }

}
