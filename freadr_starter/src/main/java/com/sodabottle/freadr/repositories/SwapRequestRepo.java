package com.sodabottle.freadr.repositories;

import com.sodabottle.freadr.models.SwapRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource(exported = false)
public interface SwapRequestRepo extends JpaRepository<SwapRequestEntity, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * from swap_request WHERE deleted = 0 AND from_user_id = :userId order by created_at desc")
    Set<SwapRequestEntity> getSentSwapRequestsForUser(@Param("userId") Long userId);


    @Query(nativeQuery = true,
            value = "SELECT * from swap_request WHERE deleted = 0 AND to_user_id = :userId order by created_at desc")
    Set<SwapRequestEntity> getReceivedSwapRequestsForUser(@Param("userId") Long userId);

    @Query(nativeQuery = true,
            value = "SELECT * from swap_request WHERE deleted = 0 AND to_user_id = :toUserId and to_book_id = :toBookId")
    Set<SwapRequestEntity> getAllActiveRequestsForThisUsersCurrentBook(@Param("toUserId") Long toUserId, @Param("toBookId") Long toBookId);
}
