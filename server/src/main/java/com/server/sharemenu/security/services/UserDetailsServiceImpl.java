package com.server.sharemenu.security.services;

import com.server.sharemenu.common.Role;
import com.server.sharemenu.common.User;
import com.server.sharemenu.exception.EmailNotConfirmedException;
import com.server.sharemenu.repositories.RoleRepository;
import com.server.sharemenu.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static java.util.Objects.isNull;

/**
 * A service interface is implemented, which is called upon every request from a client and checks in the database whether the user exists
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            logger.error("User not found!");
            throw new UsernameNotFoundException("User not found!");
        }

        if (!userRepository.findByEmailAndEmailConfirmedIsTrue(email).isPresent()) {
            logger.error("Email not confirmed");
            throw new EmailNotConfirmedException("Email not confirmed");
        }

        if (!userRepository.findUserByEmailAndEnabledIsTrue(email).isPresent()) {
            logger.error("User is not enabled");
            throw new EmailNotConfirmedException("User is disabled. Contact to administrator");
        }

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }

        user.setAuthorities(authorities);
        return user;
    }

    public Set<SimpleGrantedAuthority> packAuthorities(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }

        return authorities;
    }
}
