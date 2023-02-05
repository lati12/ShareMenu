package com.server.sharemenu.controllers;

import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resource/users")
public class UsersController {
    private  final UserRepository usersRepository;

    public UsersController(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostMapping("insert")
    public ResponseEntity<?> insertUsers(@RequestBody User users)
    {
        User newUsers = usersRepository.save(users);

        return ResponseEntity.ok(newUsers);
    }
    @GetMapping("get")
    public ResponseEntity<?> getUsers()
    {
        List<User> users = usersRepository.findAll();

        return ResponseEntity.ok(users);
    }
    @GetMapping("getById")
    public ResponseEntity<?> getUsersById(@RequestParam Long id)
    {
        Optional<User> users = usersRepository.findById(id);

        return ResponseEntity.ok(users);
    }
    @DeleteMapping("delete")
    public ResponseEntity<?> deleteUsers(@RequestParam("id") Long id)
    {
        usersRepository.deleteById(id);

        return ResponseEntity.ok("Record was deleted");
    }
}
