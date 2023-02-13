package com.server.sharemenu.repositories;

import com.server.sharemenu.common.SocialNetworkConnectivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Интерфейсът е създаден с цел комуникация с базата данни посредством Hibernate и JPA

@Repository
public interface SocialNetworkConnectivityRepository extends JpaRepository<SocialNetworkConnectivity, Long> {
    List<SocialNetworkConnectivity> findSocialNetworkConnectivitiesByUsersId(Long usersId);
    SocialNetworkConnectivity findSocialNetworkConnectivityByIdAndUsersId(Long id, Long usersId);
    void deleteSocialNetworkConnectivityByIdAndUsersId(Long id, Long usersId);

    SocialNetworkConnectivity findSocialNetworkConnectivityByKey(String key);

    SocialNetworkConnectivity findSocialNetworkConnectivityByNameAndUsersId(String name, Long usersId);
}
