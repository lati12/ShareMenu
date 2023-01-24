package com.server.sharemenu.controllers;

import com.server.sharemenu.common.Users;
import com.server.sharemenu.repositories.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UsersController {
    private  final UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostMapping("insert")
    public ResponseEntity<?> insertUsers(@RequestBody Users users)
    {
        Users newUsers = usersRepository.save(users);

        return ResponseEntity.ok(newUsers);
    }
    @GetMapping("get")
    public ResponseEntity<?> getUsers()
    {
        List<Users> users = usersRepository.findAll();

        return ResponseEntity.ok(users);
    }
    @GetMapping("getById")
    public ResponseEntity<?> getUsersById(@RequestParam Long id)
    {
        Optional<Users> users = usersRepository.findById(id);

        return ResponseEntity.ok(users);
    }
    @DeleteMapping("delete")
    public ResponseEntity<?> deleteUsers(@RequestParam("id") Long id)
    {
        usersRepository.deleteById(id);

        return ResponseEntity.ok("Record was deleted");
    }
}
