package com.server.sharemenu.repositories;

import com.server.sharemenu.models.EntityLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntitylineRepository extends JpaRepository<EntityLine, Long> {
}
