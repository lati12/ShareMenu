package com.server.sharemenu.repositories;

import com.server.sharemenu.models.UsersSocialNetworkProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersSocialNetworkProviderRepository extends JpaRepository<UsersSocialNetworkProvider, Long> {
}
