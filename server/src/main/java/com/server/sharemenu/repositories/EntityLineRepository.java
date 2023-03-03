package com.server.sharemenu.repositories;

import com.server.sharemenu.common.EntityLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface was created to communicate with the database using Hibernate and JPA
 */
@Repository
public interface EntityLineRepository extends JpaRepository<EntityLine, Long> {
    List<EntityLine> findAllByEntityHeaderId(Long entityHeaderId);
    EntityLine findEntityLineById(Long id);
    void deleteEntityLineById(Long id);
}
