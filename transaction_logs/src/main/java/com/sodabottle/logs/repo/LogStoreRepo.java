package com.sodabottle.logs.repo;

import com.sodabottle.logs.model.LogStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface LogStoreRepo extends JpaRepository<LogStoreEntity, Long> {
}
