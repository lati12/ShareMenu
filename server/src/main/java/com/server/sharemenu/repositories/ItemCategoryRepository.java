package com.server.sharemenu.repositories;

import com.server.sharemenu.models.ItemCategory;
import org.eclipse.jdt.internal.compiler.parser.JavadocParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
}
