package com.server.sharemenu.repositories;

import com.server.sharemenu.common.ShareMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface was created to communicate with the database using Hibernate and JPA
 */
@Repository
public interface ShareMenuRepository extends JpaRepository<ShareMenu, Long> {
    List<ShareMenu> findShareMenuByUsersId(Long usersId);
    ShareMenu findShareMenuByIdAndUsersId(Long id, Long usersId);
    void deleteShareMenuByIdAndUsersId(Long id, Long usersId);
}
