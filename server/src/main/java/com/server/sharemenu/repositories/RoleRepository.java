package com.server.sharemenu.repositories;

import com.server.sharemenu.common.ERole;
import com.server.sharemenu.common.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Интерфейсът е създаден с цел комуникация с базата данни посредством Hibernate и JPA

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
