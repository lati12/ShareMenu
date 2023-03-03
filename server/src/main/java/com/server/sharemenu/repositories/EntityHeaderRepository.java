package com.server.sharemenu.repositories;

import com.server.sharemenu.common.EntityHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface was created to communicate with the database using Hibernate and JPA
 */
@Repository
public interface EntityHeaderRepository extends JpaRepository<EntityHeader, Long> {
    List<EntityHeader> findEntityHeadersByUsersId(Long usersId);

    EntityHeader findEntityHeaderByIdAndUsersId(Long id, Long usersId);

    void deleteEntityHeaderByIdAndUsersId(Long id, Long usersId);
}
