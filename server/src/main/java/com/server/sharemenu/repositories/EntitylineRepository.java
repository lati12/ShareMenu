package com.server.sharemenu.repositories;

import com.server.sharemenu.models.Entityline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntitylineRepository extends JpaRepository<Entityline, Long> {
}
