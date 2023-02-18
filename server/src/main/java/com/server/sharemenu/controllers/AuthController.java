package com.server.sharemenu.controllers;

import com.server.sharemenu.common.*;
import com.server.sharemenu.repositories.EmailConfirmationRepository;
import com.server.sharemenu.repositories.RoleRepository;
import com.server.sharemenu.repositories.UserRepository;
import com.server.sharemenu.request.LoginRequest;
import com.server.sharemenu.request.RegisterRequest;
import com.server.sharemenu.response.JwtResponse;
import com.server.sharemenu.response.MessageResponse;
import com.server.sharemenu.security.jwt.JwtUtils;
import com.server.sharemenu.security.services.UserDetailsImpl;
import com.server.sharemenu.services.EmailService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/*
Класът служи за консумиране на end-poinds от ресурса Auth
и после за отделните методи
 */

@CrossOrigin(origins = {"http://sharemenu.eu", "http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private EmailService emailService;
    @Value("${sharemenu.email.template.confirmation}")
    private String emailVerificationTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EmailConfirmationRepository emailConfirmationRepository;

    @Autowired
    JwtUtils jwtUtils;

//Методът служи за влизането в регистрирания профил
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                roles,
                userDetails.getName(),
                userDetails.getLastName()));
    }
    // Методът служи за регистриране на профил
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) throws MessagingException, TemplateException, IOException {
        if (userRepository.existsByEmail(registerRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use"));
        }

        User user = new User(registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()),
                false,
                registerRequest.getName(),
                registerRequest.getLastName(),
                registerRequest.getCompanyName()
        );



        String hash = jwtUtils.generateHash();

        EmailDetails recepient = getRecipient(hash, user.getEmail(), "Confirm email",
                "Please confirm your email with following email");
        CompletableFuture<Boolean> cf = emailService.sendMailFuture(recepient,
                emailVerificationTemplate);

        cf.handle((result, ex) -> {
            if (result != null && result) {
                System.out.print("Email has been sent to: " + user.getEmail());
                EmailConfirmation emailConfirmation = new EmailConfirmation();
                emailConfirmation.setConfirmed(false);
                emailConfirmation.setHash(hash);
                emailConfirmation.setUser(user);
                emailConfirmationRepository.save(emailConfirmation);
                return true;
            } else {
                System.out.print("Error while sending mail: " + ex.toString());
                return false;

            }

        });

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER).get();
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    private EmailDetails getRecipient(String hash, String email, String subject, String text) {
        return new EmailDetails(hash, Collections.singletonList(email), subject, text);
    }
    // Методът служи за верифициране на профила
    @PostMapping(value = "/verify")
    @Transactional
    public ResponseEntity<?> verify(@RequestParam(value = "hash") String hash) {

        EmailConfirmation emailVerification = emailConfirmationRepository.findByHash(hash);

        if (emailVerification != null) {
            User user = emailVerification.getUser();

            user.setEmailConfirmed(true);// verify user
            emailConfirmationRepository.delete(emailVerification);
            userRepository.save(user);
            return ResponseEntity.ok("user validated");
        }
        return ResponseEntity.ok("Done");
    }
}
