package com.server.sharemenu.controllers;

import com.server.sharemenu.common.Item;
import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.ItemRepository;
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
@RequestMapping("/api/resource/item")
public class ItemController {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public ItemController(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/insert")
    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> insertItem(@RequestBody Item item, Principal principal){

        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {
            item.setUsers(user.get());
            Item newItem = itemRepository.save(item);
            return ResponseEntity.ok(newItem);
        }

        return ResponseEntity.ok("Item not has added. Contact to admin");
    }
    @GetMapping("/get")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getItem(Principal principal){

        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {
            List<Item> items = itemRepository.findItemsByUsersId(user.get().getId());
            return ResponseEntity.ok(items);
        }

        return ResponseEntity.ok(new ArrayList<Item>());
    }
    @GetMapping("/getById")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getItemById(@RequestParam Long id, Principal principal){

        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {
            Item item = itemRepository.findItemByIdAndUsersId(id, user.get().getId());
            return ResponseEntity.ok(item);
        }

        return ResponseEntity.ok(new Item());
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public ResponseEntity<?> deleteItem(@RequestParam(value = "id") Long id, Principal principal) {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if(user.isPresent()) {
            itemRepository.deleteItemByIdAndUsersId(id, user.get().getId());
            return ResponseEntity.ok("Record has been deleted");
        }

        return ResponseEntity.ok("Delete record not allowed");
    }

}
