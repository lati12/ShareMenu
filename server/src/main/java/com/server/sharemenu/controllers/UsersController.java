package com.server.sharemenu.controllers;

import com.server.sharemenu.common.User;
import com.server.sharemenu.exception.CustomException;
import com.server.sharemenu.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.NestedServletException;

import java.util.List;
import java.util.Optional;

/**
 * The class serves to consume end-points from the Users resource
 * and then for the individual methods
 */

@RestController
@RequestMapping("/api/resource/users")
public class UsersController {
    private  final UserRepository usersRepository;

    public UsersController(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostMapping("insert")
    /**
     * The method serves to produce a record in the database
     */
    public ResponseEntity<?> insertUsers(@RequestBody User users) throws CustomException {
        if(users.getName().equals(""))
            throw new CustomException("The element name is required");
        if(users.getLastname().equals(""))
            throw new CustomException("The last name of the element is required");
        if(users.getPassword() == "")
            throw new CustomException("Item password is required");
        if(users.getEmail() == "")
            throw new CustomException("Item email is required");
        if(users.getCompanyName() == "")
            throw new CustomException("The company is required");


        User currentUser = usersRepository.findByEmail(users.getEmail());
        if(currentUser == null) {
            throw new CustomException("User not found with email: " + users.getEmail());
        }


        if(!users.getName().equals(currentUser.getName())) {
            currentUser.setName(currentUser.getName());
        }

        if(!users.getLastname().equals(currentUser.getLastname())) {
            currentUser.setLastname(users.getLastname());
        }

        if(!users.getCompanyName().equals(currentUser.getCompanyName())) {
            currentUser.setCompanyName(users.getCompanyName());
        }

        if(users.isEnabled() != currentUser.isEnabled()) {
            currentUser.setEnabled(users.isEnabled());
        }

        if(users.getEmailConfirmed() != currentUser.getEmailConfirmed()) {
            currentUser.setEmailConfirmed(users.getEmailConfirmed());
        }

        usersRepository.save(currentUser);

        return ResponseEntity.ok(currentUser);
    }
    @GetMapping("get")
    /**
     * The method serves to consume records as the data is filtered by the User making the request
     */
    public ResponseEntity<?> getUsers()
    {
        List<User> users = usersRepository.findAll();

        return ResponseEntity.ok(users);
    }
    @GetMapping("getById")
    /**
     * The method serves to consume records as the data is filtered by the User making the request
     */
    public ResponseEntity<?> getUsersById(@RequestParam Long id)
    {
        Optional<User> users = usersRepository.findById(id);

        return ResponseEntity.ok(users);
    }
    @DeleteMapping("delete")
    /**
     * The method serves to delete a record from the database
     */
    public ResponseEntity<?> deleteUsers(@RequestParam("id") Long id)
    {
        usersRepository.deleteById(id);

        return ResponseEntity.ok("Record was deleted");
    }
}
