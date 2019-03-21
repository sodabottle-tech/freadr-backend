package com.sodabottle.freadr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sodabottle.freadr.models.Message;

@RepositoryRestResource(exported = false)
public interface MessageRepo extends JpaRepository<Message, Integer>{


}
