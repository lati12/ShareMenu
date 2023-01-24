package com.server.sharemenu.repositories;

import com.server.sharemenu.common.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long > {
}
