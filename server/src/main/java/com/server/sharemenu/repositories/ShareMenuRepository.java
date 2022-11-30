package com.server.sharemenu.repositories;

import com.server.sharemenu.models.ShareMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareMenuRepository extends JpaRepository<ShareMenu, Long> {
}
