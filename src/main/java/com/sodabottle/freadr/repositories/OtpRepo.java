package com.sodabottle.freadr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sodabottle.freadr.models.Otp;

@RepositoryRestResource(exported = false)
public interface OtpRepo extends JpaRepository<Otp, Integer> {

	@Query(nativeQuery=true, value = "SELECT * from otp WHERE mobile = :mobile AND verified = :verified order by created_at desc limit 1")
	Otp findTopOneByMobileAndVerifiedOrderByCreatedAtDesc(@Param("mobile") String mobile, @Param("verified") boolean verified);

}
