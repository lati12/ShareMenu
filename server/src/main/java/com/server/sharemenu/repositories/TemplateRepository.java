package com.server.sharemenu.repositories;

import com.server.sharemenu.common.ItemCategory;
import com.server.sharemenu.common.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {

    Template findTemplateByName(String name);
    List<Template> findTemplatesByUsersId(Long usersId);

}
