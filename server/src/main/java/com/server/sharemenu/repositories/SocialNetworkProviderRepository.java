package com.server.sharemenu.repositories;

import com.server.sharemenu.common.SocialNetworkProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialNetworkProviderRepository extends JpaRepository<SocialNetworkProvider, Long> {
}
