package com.server.sharemenu.repositories;

import com.server.sharemenu.common.ItemCategory;
import com.server.sharemenu.common.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface was created to communicate with the database using Hibernate and JPA
 */
@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {

    Template findTemplateByName(String name);
    List<Template> findTemplatesByUsersId(Long usersId);

}
