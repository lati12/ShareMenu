package com.server.sharemenu.controllers;

import com.server.sharemenu.common.UsersSocialNetworkProvider;
import com.server.sharemenu.repositories.UsersSocialNetworkProviderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/userssocialnetworkprovider")
public class UsersSocialNetworkProviderController {
    private final UsersSocialNetworkProviderRepository usersSocialNetworkProviderRepository;

    public UsersSocialNetworkProviderController(UsersSocialNetworkProviderRepository usersSocialNetworkProviderRepository) {
        this.usersSocialNetworkProviderRepository = usersSocialNetworkProviderRepository;
    }
    @PostMapping("insert")
    public ResponseEntity<?> insertUsersSocialNetworkProvider(UsersSocialNetworkProvider usersSocialNetworkProvider)
    {
        UsersSocialNetworkProvider newUsersSocialNetworkProvider = usersSocialNetworkProviderRepository.save(usersSocialNetworkProvider);

        return ResponseEntity.ok(newUsersSocialNetworkProvider);
    }
    @GetMapping("get")
    public ResponseEntity<?> getUsersSocialNetworkProvider()
    {
        List<UsersSocialNetworkProvider> usersSocialNetworkProviders = usersSocialNetworkProviderRepository.findAll();

        return ResponseEntity.ok(usersSocialNetworkProviders);
    }
    @GetMapping("getById")
    public ResponseEntity<?> getUsersSocialNetworkProviderId(@RequestParam Long id) {
        Optional<UsersSocialNetworkProvider> usersSocialNetworkProvider = usersSocialNetworkProviderRepository.findById(id);

        return ResponseEntity.ok(usersSocialNetworkProvider);
    }
    @DeleteMapping("delete")
    public  ResponseEntity<?> deleteSocialNetworkProvider(@RequestParam("id") Long id )
    {
        usersSocialNetworkProviderRepository.findById(id);

        return ResponseEntity.ok("Record was deleted");

    }
}
