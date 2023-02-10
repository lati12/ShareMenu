package com.server.sharemenu.controllers;

import com.server.sharemenu.common.UsersSocialNetworkProvider;
import com.server.sharemenu.repositories.UsersSocialNetworkProviderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
Класът служи за консумиране на end-poinds от ресурса UsersSocialNetworkProvider
и после за отделните методи
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resource/userssocialnetworkprovider")
public class UsersSocialNetworkProviderController {
    private final UsersSocialNetworkProviderRepository usersSocialNetworkProviderRepository;

    public UsersSocialNetworkProviderController(UsersSocialNetworkProviderRepository usersSocialNetworkProviderRepository) {
        this.usersSocialNetworkProviderRepository = usersSocialNetworkProviderRepository;
    }
    // Методът служи за продуциране на запис в базата данни
    @PostMapping("insert")
    public ResponseEntity<?> insertUsersSocialNetworkProvider(UsersSocialNetworkProvider usersSocialNetworkProvider)
    {
        UsersSocialNetworkProvider newUsersSocialNetworkProvider = usersSocialNetworkProviderRepository.save(usersSocialNetworkProvider);

        return ResponseEntity.ok(newUsersSocialNetworkProvider);
    }
    // Методът служи за консумиране на записи като данните са филтрирани по User, който прави заявката
    @GetMapping("get")
    public ResponseEntity<?> getUsersSocialNetworkProvider()
    {
        List<UsersSocialNetworkProvider> usersSocialNetworkProviders = usersSocialNetworkProviderRepository.findAll();

        return ResponseEntity.ok(usersSocialNetworkProviders);
    }
    // Методът служи за консумиране на записи като данните са филтрирани по User, който прави заявката
    @GetMapping("getById")
    public ResponseEntity<?> getUsersSocialNetworkProviderId(@RequestParam Long id) {
        Optional<UsersSocialNetworkProvider> usersSocialNetworkProvider = usersSocialNetworkProviderRepository.findById(id);

        return ResponseEntity.ok(usersSocialNetworkProvider);
    }
    // Методът служи за изтриване на запис от базата данни
    @DeleteMapping("delete")
    public  ResponseEntity<?> deleteSocialNetworkProvider(@RequestParam("id") Long id )
    {
        usersSocialNetworkProviderRepository.findById(id);

        return ResponseEntity.ok("Record was deleted");

    }
}
