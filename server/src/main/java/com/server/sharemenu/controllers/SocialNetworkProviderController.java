package com.server.sharemenu.controllers;

import com.server.sharemenu.models.SocialNetworkProvider;
import com.server.sharemenu.repositories.SocialNetworkProviderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/socialNetworkProvider")
public class SocialNetworkProviderController {
    private final SocialNetworkProviderRepository socialNetworkProviderRepository;

    public SocialNetworkProviderController(SocialNetworkProviderRepository socialNetworkProviderRepository) {
        this.socialNetworkProviderRepository = socialNetworkProviderRepository;
    }
    @PostMapping("insert")
    public ResponseEntity<?> insertSocialNetworkProvider(SocialNetworkProvider socialNetworkProvider)
    {
        SocialNetworkProvider newSocialNetworkProvider = socialNetworkProviderRepository.save(socialNetworkProvider);

        return ResponseEntity.ok(newSocialNetworkProvider);
    }
    @GetMapping("get")
    public ResponseEntity<?> getSocialNetworkProvider()
    {
        List<SocialNetworkProvider> socialNetworkProviders = socialNetworkProviderRepository.findAll();

        return ResponseEntity.ok(socialNetworkProviders);
    }
    @GetMapping("getById")
    public ResponseEntity<?> getSocialNetworkProviderById(@RequestParam Long id)
    {
        Optional<SocialNetworkProvider> socialNetworkProvider = socialNetworkProviderRepository.findById(id);

        return ResponseEntity.ok(socialNetworkProvider);
    }
    @DeleteMapping("delete")
    public ResponseEntity<?> deleteSocialNetworkProvider(@RequestParam("id") Long id)
    {
        socialNetworkProviderRepository.deleteById(id);

        return ResponseEntity.ok("Record was deleted");

    }
}
