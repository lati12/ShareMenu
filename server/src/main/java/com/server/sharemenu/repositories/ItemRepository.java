package com.server.sharemenu.repositories;

import com.server.sharemenu.common.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface was created to communicate with the database using Hibernate and JPA
 */

@Repository
public interface ItemRepository extends JpaRepository<Item, Long > {
    List<Item> findItemsByUsersId(Long usersId);
    Item findItemByIdAndUsersId(Long id, Long usersId);
    void deleteItemByIdAndUsersId(Long id, Long usersId);
    boolean existsItemByItemCategoryId(Long itemCategoryId);
}
