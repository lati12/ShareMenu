package com.server.sharemenu.controllers;

import com.server.sharemenu.common.ItemCategory;
import com.server.sharemenu.common.User;
import com.server.sharemenu.exception.CustomException;
import com.server.sharemenu.repositories.ItemCategoryRepository;
import com.server.sharemenu.repositories.ItemRepository;
import com.server.sharemenu.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * The class serves to consume endpoints from the Item Category resource
 * and then for the individual methods
 */

@RestController
@RequestMapping("/api/resource/itemcategory")
public class ItemCategoryController {
    private final ItemCategoryRepository itemCategoryRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public ItemCategoryController(ItemCategoryRepository itemCategoryRepository, UserRepository userRepository, ItemRepository itemRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }
    @PostMapping("/insert")
    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    /**
     * The method serves to produce a record in the database
     */
    public ResponseEntity<?> insertItemCategory(@RequestBody ItemCategory itemCategory, Principal principal) throws CustomException {
        Scanner in = new Scanner(System.in);
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {
            if (itemCategory.getName().equals(""))
                throw new CustomException("The element name is required");

            if (itemCategory.getPosition() == 0)
                throw new CustomException("Item position is required");


            itemCategory.setUsers(user.get());
            ItemCategory newItemCategory = itemCategoryRepository.save(itemCategory);
            return ResponseEntity.ok(newItemCategory);
        }

        return ResponseEntity.ok("Record not allowed to save.");
    }
    @GetMapping("/get")
    @PreAuthorize("hasRole('ROLE_USER')")
    /**
     * The method serves to consume records as the data is filtered by the User making the request
     */
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
    /**
     * The method serves to consume records as the data is filtered by the User making the request
     */
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
    @Transactional(rollbackFor = Exception.class)
    /**
     * The method serves to delete a record from the database
     */
    public ResponseEntity<?> deleteItemCategory(@RequestParam(value = "id") Long id, Principal principal) throws CustomException {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {

            boolean hasItem = itemRepository.existsItemByItemCategoryId(id);

            if(hasItem)
                throw new CustomException("Record has references to items");

            itemCategoryRepository.deleteItemCategoryByIdAndUsersId(id, user.get().getId());
            return ResponseEntity.ok("Record has been deleted");
        }
        return ResponseEntity.ok("Delete record not allowed");
    }
}
