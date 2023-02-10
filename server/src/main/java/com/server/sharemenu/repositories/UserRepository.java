package com.server.sharemenu.repositories;

import com.server.sharemenu.common.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Интерфейсът е създаден с цел комуникация с базата данни посредством Hibernate и JPA


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndEmailConfirmedIsTrue(String email);
    Boolean existsByEmail(String email);
}
