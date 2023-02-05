package com.server.sharemenu.controllers;

import com.server.sharemenu.common.ItemCategory;
import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.ItemCategoryRepository;
import com.server.sharemenu.repositories.UserRepository;
import org.springframework.cache.annotation.SpringCacheAnnotationParser;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/resource/itemcategory")
public class ItemCategoryController {
    private final ItemCategoryRepository itemCategoryRepository;
    private final UserRepository userRepository;

    public ItemCategoryController(ItemCategoryRepository itemCategoryRepository, UserRepository userRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertItemCategory(@RequestBody ItemCategory itemCategory){
        ItemCategory newItemCategory = itemCategoryRepository.save(itemCategory);

        return ResponseEntity.ok(newItemCategory);
    }
    @GetMapping("/get")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getItemCategory(@AuthenticationPrincipal User auth){

        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(auth.getEmail());

        if(user.isPresent())
        {
            List<ItemCategory> itemCategories = itemCategoryRepository.findItemCategoriesByUsersId(user.get().getId());
            return ResponseEntity.ok(itemCategories);
        }

        return ResponseEntity.ok(new ArrayList<ItemCategory>());
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
