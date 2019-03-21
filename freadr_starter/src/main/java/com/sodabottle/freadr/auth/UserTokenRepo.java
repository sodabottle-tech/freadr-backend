package com.sodabottle.freadr.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface UserTokenRepo extends JpaRepository<UserToken, Long> {
    Optional<UserToken> findByUserId(String userId);
}
