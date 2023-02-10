package com.server.sharemenu.controllers;

import com.server.sharemenu.common.ItemCategory;
import com.server.sharemenu.common.SocialNetworkProvider;
import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.SocialNetworkProviderRepository;
import com.server.sharemenu.repositories.UserRepository;
import com.server.sharemenu.response.facebook.FacebookAccountPackResponse;
import com.server.sharemenu.response.facebook.FacebookAccountsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
Класът служи за консумиране на end-poinds от ресурса SocialNetworkProvider
и после за отделните методи
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resource/socialNetworkProvider")
public class SocialNetworkProviderController {

    @Value("${sharemenu.facebook.appid}")
    private String facebookAppId;

    @Value("${sharemenu.facebook.secretid}")
    private String facebookSecretId;

    @Value("${sharemenu.facebook.access-token-extended}")
    private String facebookAccessToken;

    private final RestTemplate restTemplate;


    private final SocialNetworkProviderRepository socialNetworkProviderRepository;
    private final UserRepository userRepository;

    public SocialNetworkProviderController(SocialNetworkProviderRepository socialNetworkProviderRepository, UserRepository userRepository, RestTemplateBuilder restTemplateBuilder) {
        this.socialNetworkProviderRepository = socialNetworkProviderRepository;
        this.userRepository = userRepository;
        this.restTemplate = restTemplateBuilder.build();
    }
    // Методът служи за продуциране на запис в базата данни
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
    // Методът служи за консумиране на записи като данните са филтрирани по User, който прави заявката
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
    // Методът служи за консумиране на записи като данните са филтрирани по User, който прави заявката
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
    // Методът служи за изтриване на запис от базата данни
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

    @GetMapping("/findSocialNetworkPage")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> findSocialNetworkPage(@RequestParam("page_name") String pageName, Principal principal) {
        //filter page
        //after find the page set a record in database

        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()) {

            SocialNetworkProvider socialNetworkProvider = socialNetworkProviderRepository.findSocialNetworkProviderByNameAndUsersId(pageName, user.get().getId());
            if (socialNetworkProvider != null) {
                return ResponseEntity.ok("Already exists");
            }

            String url = "https://graph.facebook.com/me/accounts?fields=name,access_token&access_token=" + facebookAccessToken;
            FacebookAccountPackResponse facebookAccountPackResponse = restTemplate.getForObject(url, FacebookAccountPackResponse.class);

            for (FacebookAccountsResponse facebookAccountsResponse : facebookAccountPackResponse.getData()) {
                if (facebookAccountsResponse.getName().equals(pageName)) {
                    socialNetworkProvider = new SocialNetworkProvider();
                    socialNetworkProvider.setName(pageName);
                    socialNetworkProvider.setAccessToken(facebookAccountsResponse.getAccess_token());
                    socialNetworkProvider.setUsers(user.get());
                    socialNetworkProvider.setKey(facebookAccountsResponse.getId());
                    socialNetworkProviderRepository.save(socialNetworkProvider);
                }
            }
        }

        return ResponseEntity.ok("Done");
    }
}
