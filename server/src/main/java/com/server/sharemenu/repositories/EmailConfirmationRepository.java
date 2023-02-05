package com.server.sharemenu.repositories;

import com.server.sharemenu.common.EmailConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailConfirmationRepository extends JpaRepository<EmailConfirmation, Long> {
}
