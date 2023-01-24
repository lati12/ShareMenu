package com.server.sharemenu.repositories;

import com.server.sharemenu.common.EntityLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntitylineRepository extends JpaRepository<EntityLine, Long> {
    List<EntityLine> findAllByEntityHeaderId(Long entityHeaderId);
}
