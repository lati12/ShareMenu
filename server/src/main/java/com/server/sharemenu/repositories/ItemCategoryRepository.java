package com.server.sharemenu.repositories;

import com.server.sharemenu.common.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Интерфейсът е създаден с цел комуникация с базата данни посредством Hibernate и JPA


@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
    List<ItemCategory> findItemCategoriesByUsersId(Long usersId);
    ItemCategory findItemCategoryByIdAndUsersId(Long id, Long usersId);
    void deleteItemCategoryByIdAndUsersId(Long id, Long usersId);
}
