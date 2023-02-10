package com.server.sharemenu.repositories;

import com.server.sharemenu.common.SocialNetworkProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Интерфейсът е създаден с цел комуникация с базата данни посредством Hibernate и JPA

@Repository
public interface SocialNetworkProviderRepository extends JpaRepository<SocialNetworkProvider, Long> {
    List<SocialNetworkProvider> findSocialNetworkProviderByUsersId(Long usersId);
    SocialNetworkProvider findSocialNetworkProviderByIdAndUsersId(Long id, Long usersId);
    void deleteSocialNetworkProviderByIdAndUsersId(Long id, Long usersId);

    SocialNetworkProvider findSocialNetworkProviderByKey(String key);

    SocialNetworkProvider findSocialNetworkProviderByNameAndUsersId(String name, Long usersId);
}
