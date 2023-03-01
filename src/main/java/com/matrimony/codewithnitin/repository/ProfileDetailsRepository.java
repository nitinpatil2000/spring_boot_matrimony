package com.matrimony.codewithnitin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matrimony.codewithnitin.entity.ProfileDetails;


public interface ProfileDetailsRepository extends JpaRepository<ProfileDetails , Integer> {

	Optional<ProfileDetails> findById(Integer profileDetailsId);

	    @Query("SELECT COUNT(pd) FROM ProfileDetails pd JOIN pd.profile p WHERE p.id = :profileId")
	    Optional<Object[]> countByProfileId(@Param("profileId") Integer profileId);
	

	
}