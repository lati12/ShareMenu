package com.server.sharemenu.controllers;

import com.server.sharemenu.common.Item;
import com.server.sharemenu.repositories.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/item")
public class ItemController {
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertItem(@RequestBody Item item){
        Item newItem = itemRepository.save(item);
        return ResponseEntity.ok(newItem);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getItem(){
        List<Item> items = itemRepository.findAll();

        return ResponseEntity.ok(items);
    }
    @GetMapping("/getById")
    public ResponseEntity<?> getItemById(@RequestParam Long id){

        Optional<Item> item = itemRepository.findById(id);

        return ResponseEntity.ok(item);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteItem(@RequestParam(value = "id") Long id)
    {
        itemRepository.deleteById(id);

        return ResponseEntity.ok("Record has been deleted");
    }

}
