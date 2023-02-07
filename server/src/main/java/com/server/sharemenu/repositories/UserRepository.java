package com.server.sharemenu.repositories;

import com.server.sharemenu.common.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndEmailConfirmedIsTrue(String email);
    Boolean existsByEmail(String email);
}
