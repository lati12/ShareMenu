package com.server.sharemenu.repositories;

import com.server.sharemenu.common.EntityHeader;
import com.server.sharemenu.common.MenuHeaderView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*
Интерфейсът е създаден с цел комуникация с базата данни и изкуствено пресъздаване на SQL View, което посредством Sql заявка
да предаде информацията в плосък вид
*/
@Repository
public interface MenuHeaderViewRepository extends JpaRepository<EntityHeader, Long> {
    @Query(nativeQuery = true, value = "SELECT users.companyName as companyName " +
    ", entityheader.email, entityheader.phone FROM entityheader" +
    " JOIN users ON users.id = entityheader.users_id " +
    " WHERE entityheader.id = :id")
    MenuHeaderView getMenuHeaderView(@Param("id") Long id);
}
