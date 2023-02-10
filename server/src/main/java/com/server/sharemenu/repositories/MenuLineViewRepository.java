package com.server.sharemenu.repositories;

import com.server.sharemenu.common.EntityLine;
import com.server.sharemenu.common.MenuLineView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*
Интерфейсът е създаден с цел комуникация с базата данни и изкуствено пресъздаване на SQL View, което посредством Sql заявка
да предаде информацията в плосък вид
*/

public interface MenuLineViewRepository extends JpaRepository<EntityLine, Long> {
    @Query(nativeQuery = true, value = "SELECT itemcategory.name as itemCategoryName, item.Name as itemName " +
    ", item.description as itemDescription, item.unit as itemUnit "+
    ", entityline.quantity, entityline.price FROM entityline " +
    "JOIN item ON item.id = entityline.item_id "+
    "JOIN itemcategory ON itemcategory.id = item.itemcategory_id " +
    "WHERE entityline.entityheader_id = :entityheader_id " +
    "ORDER BY itemcategory.position asc")
    List<MenuLineView> getMenuLineView(@Param("entityheader_id") Long entityHeaderId);
}
