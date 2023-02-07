package com.server.sharemenu.controllers;

import com.server.sharemenu.common.ItemCategory;
import com.server.sharemenu.common.SocialNetworkProvider;
import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.SocialNetworkProviderRepository;
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
@RequestMapping("/api/resource/socialNetworkProvider")
public class SocialNetworkProviderController {
    private final SocialNetworkProviderRepository socialNetworkProviderRepository;
    private final UserRepository userRepository;

    public SocialNetworkProviderController(SocialNetworkProviderRepository socialNetworkProviderRepository, UserRepository userRepository) {
        this.socialNetworkProviderRepository = socialNetworkProviderRepository;
        this.userRepository = userRepository;
    }
    @PostMapping("/insert")
    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> insertSocialNetworkProvider(@RequestBody SocialNetworkProvider socialNetworkProvider, Principal principal) {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            socialNetworkProvider.setUsers(user.get());
            SocialNetworkProvider newSocialNetworkProvider = socialNetworkProviderRepository.save(socialNetworkProvider);
            return ResponseEntity.ok(newSocialNetworkProvider);
        }

        return ResponseEntity.ok("Record not allowed to save.");
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getSocialNetworkProvider(Principal principal) {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            List<SocialNetworkProvider> socialNetworkProviders = socialNetworkProviderRepository.findSocialNetworkProviderByUsersId(user.get().getId());
            return ResponseEntity.ok(socialNetworkProviders);
        }
        return ResponseEntity.ok(new ArrayList<SocialNetworkProvider>());

    }
    @GetMapping("/getById")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getSocialNetworkProviderById(@RequestParam Long id, Principal principal) {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            SocialNetworkProvider socialNetworkProvider = socialNetworkProviderRepository.findSocialNetworkProviderByIdAndUsersId(id, user.get().getId());
            return ResponseEntity.ok(socialNetworkProvider);
        }
        return ResponseEntity.ok("Read record not allowed for user");

    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public ResponseEntity<?> deleteSocialNetworkProvider(@RequestParam("id") Long id, Principal principal) {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            socialNetworkProviderRepository.deleteSocialNetworkProviderByIdAndUsersId(id, user.get().getId());
            return ResponseEntity.ok("Record has been deleted");
        }

        return ResponseEntity.ok("Delete record not allowed");

    }
}
