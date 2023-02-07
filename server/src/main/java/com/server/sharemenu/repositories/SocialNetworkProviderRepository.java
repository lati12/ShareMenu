package com.server.sharemenu.repositories;

import com.server.sharemenu.common.SocialNetworkProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialNetworkProviderRepository extends JpaRepository<SocialNetworkProvider, Long> {
    List<SocialNetworkProvider> findSocialNetworkProviderByUsersId(Long usersId);
    SocialNetworkProvider findSocialNetworkProviderByIdAndUsersId(Long id, Long usersId);
    void deleteSocialNetworkProviderByIdAndUsersId(Long id, Long usersId);
}
