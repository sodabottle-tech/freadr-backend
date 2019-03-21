package com.sodabottle.freadr.repositories;

import com.sodabottle.freadr.models.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface MessageRepo extends JpaRepository<MessageEntity, Integer> {
}
