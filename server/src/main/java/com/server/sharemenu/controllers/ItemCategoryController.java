package com.server.sharemenu.controllers;

import com.server.sharemenu.common.ItemCategory;
import com.server.sharemenu.repositories.ItemCategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/itemcategory")
public class ItemCategoryController {
    private final ItemCategoryRepository itemCategoryRepository;

    public ItemCategoryController(ItemCategoryRepository itemCategoryRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertItemCategory(@RequestBody ItemCategory itemCategory){
        ItemCategory newItemCategory = itemCategoryRepository.save(itemCategory);

        return ResponseEntity.ok(newItemCategory);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getItemCategory(){
        List<ItemCategory> itemCategories = itemCategoryRepository.findAll();

        return ResponseEntity.ok(itemCategories);
    }
    @GetMapping("/getById")
    public ResponseEntity<?> getItemCatById(@RequestParam Long id){
        Optional<ItemCategory> itemCategory = itemCategoryRepository.findById(id);

        return ResponseEntity.ok(itemCategory);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteItemCategory(@RequestParam(value = "id") Long id){
        itemCategoryRepository.deleteById(id);

        return ResponseEntity.ok("Record has been deleted");
    }
}
