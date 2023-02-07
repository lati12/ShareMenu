package com.server.sharemenu.repositories;

import com.server.sharemenu.common.ShareMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareMenuRepository extends JpaRepository<ShareMenu, Long> {
    List<ShareMenu> findShareMenuByUsersId(Long usersId);
    ShareMenu findShareMenuByIdAndUsersId(Long id, Long usersId);
    void deleteShareMenuByIdAndUsersId(Long id, Long usersId);
}
