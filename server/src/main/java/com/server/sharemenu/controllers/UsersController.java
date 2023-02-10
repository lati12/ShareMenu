package com.server.sharemenu.controllers;

import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
Класът служи за консумиране на end-poinds от ресурса Users
и после за отделните методи
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resource/users")
public class UsersController {
    private  final UserRepository usersRepository;

    public UsersController(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // Методът служи за продуциране на запис в базата данни
    @PostMapping("insert")
    public ResponseEntity<?> insertUsers(@RequestBody User users)
    {
        User newUsers = usersRepository.save(users);

        return ResponseEntity.ok(newUsers);
    }
    // Методът служи за консумиране на записи като данните са филтрирани по User, който прави заявката
    @GetMapping("get")
    public ResponseEntity<?> getUsers()
    {
        List<User> users = usersRepository.findAll();

        return ResponseEntity.ok(users);
    }
    // Методът служи за консумиране на записи като данните са филтрирани по User, който прави заявката
    @GetMapping("getById")
    public ResponseEntity<?> getUsersById(@RequestParam Long id)
    {
        Optional<User> users = usersRepository.findById(id);

        return ResponseEntity.ok(users);
    }
    // Методът служи за изтриване на запис от базата данни
    @DeleteMapping("delete")
    public ResponseEntity<?> deleteUsers(@RequestParam("id") Long id)
    {
        usersRepository.deleteById(id);

        return ResponseEntity.ok("Record was deleted");
    }
}
