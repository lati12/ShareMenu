package com.server.sharemenu.repositories;

import com.server.sharemenu.models.EntityHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityHeaderRepository extends JpaRepository<EntityHeader, Long> {
}
