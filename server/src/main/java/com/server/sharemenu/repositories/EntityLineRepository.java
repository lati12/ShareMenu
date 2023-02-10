package com.server.sharemenu.repositories;

import com.server.sharemenu.common.EntityLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Интерфейсът е създаден с цел комуникация с базата данни посредством Hibernate и JPA

@Repository
public interface EntityLineRepository extends JpaRepository<EntityLine, Long> {
    List<EntityLine> findAllByEntityHeaderId(Long entityHeaderId);
}
