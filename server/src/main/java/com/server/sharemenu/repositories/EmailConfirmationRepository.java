package com.server.sharemenu.repositories;

import com.server.sharemenu.common.EmailConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface was created to communicate with the database using Hibernate and JPA
 */
public interface EmailConfirmationRepository extends JpaRepository<EmailConfirmation, Long> {
    EmailConfirmation findByHash(String hash);
}
