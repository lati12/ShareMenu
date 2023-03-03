package com.server.sharemenu.repositories;

import com.server.sharemenu.common.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface was created to communicate with the database using Hibernate and JPA
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndEmailConfirmedIsTrue(String email);
    Boolean existsByEmail(String email);

    Optional<User> findUserByEmailAndEnabledIsTrue(String email);
    User findByEmail(String email);
}
