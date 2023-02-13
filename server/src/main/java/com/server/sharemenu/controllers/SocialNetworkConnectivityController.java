package com.server.sharemenu.controllers;

import com.server.sharemenu.common.SocialNetworkConnectivity;
import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.SocialNetworkConnectivityRepository;
import com.server.sharemenu.repositories.UserRepository;
import com.server.sharemenu.response.facebook.FacebookAccountPackResponse;
import com.server.sharemenu.response.facebook.FacebookAccountsResponse;
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
Класът служи за консумиране на end-poinds от ресурса SocialNetworkConnectivity
и после за отделните методи
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resource/socialNetworkConnectivity")
public class SocialNetworkConnectivityController {

    private final RestTemplate restTemplate;


    private final SocialNetworkConnectivityRepository socialNetworkConnectivityRepository;
    private final UserRepository userRepository;

    public SocialNetworkConnectivityController(SocialNetworkConnectivityRepository socialNetworkConnectivityRepository, UserRepository userRepository, RestTemplateBuilder restTemplateBuilder) {
        this.socialNetworkConnectivityRepository = socialNetworkConnectivityRepository;
        this.userRepository = userRepository;
        this.restTemplate = restTemplateBuilder.build();
    }
    // Методът служи за продуциране на запис в базата данни
    @PostMapping("/insert")
    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> insertSocialNetworkConnectivity(@RequestBody SocialNetworkConnectivity socialNetworkConnectivity, Principal principal) {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            socialNetworkConnectivity.setUsers(user.get());
            SocialNetworkConnectivity newSocialNetworkConnectivity = socialNetworkConnectivityRepository.save(socialNetworkConnectivity);
            return ResponseEntity.ok(newSocialNetworkConnectivity);
        }

        return ResponseEntity.ok("Record not allowed to save.");
    }
    // Методът служи за консумиране на записи като данните са филтрирани по User, който прави заявката
    @GetMapping("/get")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getSocialNetworkConnectivity(Principal principal) {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            List<SocialNetworkConnectivity> socialNetworkConnectivities = socialNetworkConnectivityRepository.findSocialNetworkConnectivitiesByUsersId(user.get().getId());
            return ResponseEntity.ok(socialNetworkConnectivities);
        }
        return ResponseEntity.ok(new ArrayList<SocialNetworkConnectivity>());

    }
    // Методът служи за консумиране на записи като данните са филтрирани по User, който прави заявката
    @GetMapping("/getById")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getSocialNetworkConnectivityById(@RequestParam Long id, Principal principal) {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            SocialNetworkConnectivity socialNetworkConnectivity = socialNetworkConnectivityRepository.findSocialNetworkConnectivityByIdAndUsersId(id, user.get().getId());
            return ResponseEntity.ok(socialNetworkConnectivity);
        }
        return ResponseEntity.ok("Read record not allowed for user");

    }
    // Методът служи за изтриване на запис от базата данни
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public ResponseEntity<?> deleteSocialNetworkConnectivity(@RequestParam("id") Long id, Principal principal) {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            socialNetworkConnectivityRepository.deleteSocialNetworkConnectivityByIdAndUsersId(id, user.get().getId());
            return ResponseEntity.ok("Record has been deleted");
        }

        return ResponseEntity.ok("Delete record not allowed");

    }

    @PostMapping("/findSocialNetworkPage")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> findSocialNetworkPage(@RequestBody SocialNetworkConnectivity socialNetworkConnectivity, Principal principal) {
        //filter page
        //after find the page set a record in database
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()) {
            SocialNetworkConnectivity local = socialNetworkConnectivityRepository.findSocialNetworkConnectivityByNameAndUsersId(socialNetworkConnectivity.getName(), user.get().getId());
            if (local != null) {
                return ResponseEntity.ok("Already exists");
            }

            String url = "https://graph.facebook.com/me/accounts?fields=name,access_token&access_token=" + socialNetworkConnectivity.getAccessToken();
            FacebookAccountPackResponse facebookAccountPackResponse = restTemplate.getForObject(url, FacebookAccountPackResponse.class);

            for (FacebookAccountsResponse facebookAccountsResponse : facebookAccountPackResponse.getData()) {
                if (facebookAccountsResponse.getName().equals(socialNetworkConnectivity.getName())) {
                    socialNetworkConnectivity.setName(socialNetworkConnectivity.getName());
                    socialNetworkConnectivity.setAccessToken(facebookAccountsResponse.getAccess_token());
                    socialNetworkConnectivity.setUsers(user.get());
                    socialNetworkConnectivity.setKey(facebookAccountsResponse.getId());
                    socialNetworkConnectivityRepository.save(socialNetworkConnectivity);
                }
            }
        }

        return ResponseEntity.ok("Done");
    }
}
