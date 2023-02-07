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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
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
    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> insertItemCategory(@RequestBody ItemCategory itemCategory, Principal principal){

        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {
            itemCategory.setUsers(user.get());
            ItemCategory newItemCategory = itemCategoryRepository.save(itemCategory);
            return ResponseEntity.ok(newItemCategory);
        }

        return ResponseEntity.ok("Record not allowed to save.");
    }
    @GetMapping("/get")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getItemCategory(Principal principal){

        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent())
        {
            List<ItemCategory> itemCategories = itemCategoryRepository.findItemCategoriesByUsersId(user.get().getId());
            return ResponseEntity.ok(itemCategories);
        }

        return ResponseEntity.ok(new ArrayList<ItemCategory>());
    }
    @GetMapping("/getById")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getItemCatById(@RequestParam Long id, Principal principal){

        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()) {
            ItemCategory itemCategory = itemCategoryRepository.findItemCategoryByIdAndUsersId(id, user.get().getId());
            return ResponseEntity.ok(itemCategory);
        }

        return ResponseEntity.ok("Read record not allowed for user");
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public ResponseEntity<?> deleteItemCategory(@RequestParam(value = "id") Long id, Principal principal){
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {
            itemCategoryRepository.deleteItemCategoryByIdAndUsersId(id, user.get().getId());
            return ResponseEntity.ok("Record has been deleted");
        }

        return ResponseEntity.ok("Delete record not allowed");
    }
}
