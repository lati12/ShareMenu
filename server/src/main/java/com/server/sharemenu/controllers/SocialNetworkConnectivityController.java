package com.server.sharemenu.controllers;

import com.server.sharemenu.common.SocialNetworkConnectivity;
import com.server.sharemenu.common.User;
import com.server.sharemenu.exception.CustomException;
import com.server.sharemenu.repositories.SocialNetworkConnectivityRepository;
import com.server.sharemenu.repositories.UserRepository;
import com.server.sharemenu.response.facebook.FacebookAccountPackResponse;
import com.server.sharemenu.response.facebook.FacebookAccountsResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import org.springframework.transaction.annotation.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The class serves to consume endpoints from the Social Network Connectivity resource
 * and then for the individual methods
 */

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
    @PostMapping("/insert")
    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    /**
     * The method serves to produce a record in the database
     */
    public ResponseEntity<?> insertSocialNetworkConnectivity(@RequestBody SocialNetworkConnectivity socialNetworkConnectivity, Principal principal) throws CustomException {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){

            socialNetworkConnectivity.setUsers(user.get());
            SocialNetworkConnectivity newSocialNetworkConnectivity = socialNetworkConnectivityRepository.save(socialNetworkConnectivity);
            return ResponseEntity.ok(newSocialNetworkConnectivity);
        }

        return ResponseEntity.ok("Record not allowed to save.");
    }
    @GetMapping("/get")
    @PreAuthorize("hasRole('ROLE_USER')")
    /**
     * The method serves to consume records as the data is filtered by the User making the request
     */
    public ResponseEntity<?> getSocialNetworkConnectivity(Principal principal) {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            List<SocialNetworkConnectivity> socialNetworkConnectivities = socialNetworkConnectivityRepository.findSocialNetworkConnectivitiesByUsersId(user.get().getId());
            return ResponseEntity.ok(socialNetworkConnectivities);
        }
        return ResponseEntity.ok(new ArrayList<SocialNetworkConnectivity>());

    }
    @GetMapping("/getById")
    @PreAuthorize("hasRole('ROLE_USER')")
    /**
     * The method serves to consume records as the data is filtered by the User making the request
     */
    public ResponseEntity<?> getSocialNetworkConnectivityById(@RequestParam Long id, Principal principal) {
        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()){
            SocialNetworkConnectivity socialNetworkConnectivity = socialNetworkConnectivityRepository.findSocialNetworkConnectivityByIdAndUsersId(id, user.get().getId());
            return ResponseEntity.ok(socialNetworkConnectivity);
        }
        return ResponseEntity.ok("Read record not allowed for user");

    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    /**
     * The method serves to delete a record from the database
     */
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
    /**
     * The method serves to find a social network
     */
    public ResponseEntity<?> findSocialNetworkPage(@RequestBody SocialNetworkConnectivity socialNetworkConnectivity, Principal principal) throws CustomException {

        Optional<User> user = userRepository.findByEmailAndEmailConfirmedIsTrue(principal.getName());

        if (user.isPresent()) {
            if(socialNetworkConnectivity.getAccessToken().equals(""))
                throw new CustomException("The element token is required");
            if(socialNetworkConnectivity.getName().equals(""))
                throw new CustomException("The element name is required");
            if(socialNetworkConnectivity.getAppId().equals(""))
                throw new CustomException("Application Item Id is required");
            if(socialNetworkConnectivity.getSecretId().equals(""))
                throw new CustomException("The item's secret key is required");


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
