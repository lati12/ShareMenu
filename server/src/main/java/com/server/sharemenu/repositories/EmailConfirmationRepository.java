package com.server.sharemenu.repositories;

import com.server.sharemenu.common.EmailConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Интерфейсът е създаден с цел комуникация с базата данни посредством Hibernate и JPA

public interface EmailConfirmationRepository extends JpaRepository<EmailConfirmation, Long> {
    EmailConfirmation findByHash(String hash);
}
