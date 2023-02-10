package com.server.sharemenu.repositories;

import com.server.sharemenu.common.EntityHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Интерфейсът е създаден с цел комуникация с базата данни посредством Hibernate и JPA

@Repository
public interface EntityHeaderRepository extends JpaRepository<EntityHeader, Long> {
}
