package com.server.sharemenu.controllers;

import com.server.sharemenu.common.*;
import com.server.sharemenu.exception.CustomException;
import com.server.sharemenu.exception.UserAlreadyExists;
import com.server.sharemenu.repositories.EmailConfirmationRepository;
import com.server.sharemenu.repositories.RoleRepository;
import com.server.sharemenu.repositories.TemplateRepository;
import com.server.sharemenu.repositories.UserRepository;
import com.server.sharemenu.request.LoginRequest;
import com.server.sharemenu.request.RegisterRequest;
import com.server.sharemenu.response.JwtResponse;
import com.server.sharemenu.response.MessageResponse;
import com.server.sharemenu.security.JwtProvider;
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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * The class serves to consume end-points from the Auth resource
 * and then for the individual methods
 */

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
    JwtProvider jwtProvider;

    @Autowired
    TemplateRepository templateRepository;

    @PostMapping("/register")
    @Transactional

    /**
     * The method serves to register a profile
     */
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) throws MessagingException, TemplateException, IOException, UserAlreadyExists, CustomException {
        if (registerRequest.getName().equals(""))
            throw new CustomException("The element name is required");
        if (registerRequest.getLastName().equals(""))
            throw new CustomException("The last name of the element is required");
        if (registerRequest.getCompanyName().equals(""))
            throw new CustomException("The company is required");
        if (registerRequest.getEmail().equals(""))
            throw new CustomException("Item email is required");
        if (registerRequest.getPassword().equals(""))
            throw new CustomException("Item password is required");

        if (userRepository.existsByEmail(registerRequest.getEmail())){
            throw new UserAlreadyExists("User with this email already exists!");
        }
        User user = new User(registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()),
                false,
                registerRequest.getName(),
                registerRequest.getLastName(),
                registerRequest.getCompanyName()
        );

        if (user.getEmail().equals("")
                || user.getName().equals("")
                || user.getLastname().equals("")
                || user.getPassword().equals("")) {
            throw new CustomException("Fields are required");
        }

        String hash = jwtProvider.generateHash();

        EmailDetails recepient = getRecipient(hash, user.getEmail(), "ShareMenu email confirmation",
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


        //set default template
        Set<Template> templates = new HashSet<>();
        Template userTemp = templateRepository.findById(1L).get();
        templates.add(userTemp);

        user.setTemplates(templates);
        user.setRoles(roles);
        user.setEnabled(true);
        userRepository.save(user);
        return ResponseEntity.ok("user has been created");
    }

    private EmailDetails getRecipient(String hash, String email, String subject, String text) {
        return new EmailDetails(hash, Collections.singletonList(email), subject, text, email);
    }

    @PostMapping(value = "/verify")
    @Transactional
    /**
     * The method serves to verify the profile
     */

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
