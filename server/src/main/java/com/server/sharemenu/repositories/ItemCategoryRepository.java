package com.server.sharemenu.repositories;

import com.server.sharemenu.common.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface was created to communicate with the database using Hibernate and JPA
 */

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
    List<ItemCategory> findItemCategoriesByUsersId(Long usersId);
    ItemCategory findItemCategoryByIdAndUsersId(Long id, Long usersId);
    void deleteItemCategoryByIdAndUsersId(Long id, Long usersId);
}
