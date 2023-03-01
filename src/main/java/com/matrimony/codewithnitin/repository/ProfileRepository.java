package com.matrimony.codewithnitin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matrimony.codewithnitin.entity.Profile;

//@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	
	 	

	Optional<Profile> findById(Integer profileId);

//	@Query(value = "select * from matrimonyy.profile p " +
//			"left join matrimonyy.user u on p.id= u.id " +
//			"left join matrimonyy.profile_details pd on p.id= pd.id " +
//			"left join matrimonyy.profile_family pf on p.id= pf.id " +
//			"left join matrimonyy.partner_preferance pp on p.id= pp.id " +
//			"left join matrimonyy.extended_profile_family pef on p.id=pef.id " +
//			"where p.id=?1", nativeQuery = true)
//			Optional<Profile> findProfileById(long id);

	List<Profile> findByreligion(String religion);

	List<Profile> findBycast(String cast);
	
	
	
	//Query for BasicSerach api 
	
	
//	@Query("SELECT u.firstName, u.middleName, u.lastName, u.emailId, "
//			+ "p.country, p.address1, p.address2,p.cast,p.profileName, p.religion, p.state, p.subCast, p.townCity, "
//			+ "pd.height, pd.weight, pd.birthPlace, pd.collegeUniversity, pd.company, pd.marriageStatus, pd.motherTongue "
//			+ "FROM User u " + "INNER JOIN u.profile p " + "INNER JOIN p.profileDetails pd "
//			+ "WHERE p.religion = IFNULL(NULLIF(:religion, ''), p.religion) "
//			+ "AND pd.birthPlace = IFNULL(NULLIF(:birthPlace, ''), pd.birthPlace) "
//			+ "AND pd.marriageStatus = IFNULL(NULLIF(:marriageStatus, ''), pd.marriageStatus) "
//			+ "AND pd.motherTongue = IFNULL(NULLIF(:motherTongue, ''), pd.motherTongue) "
//			+ "AND p.state = IFNULL(NULLIF(:state, ''), p.state) "
//			+ "AND p.subCast = IFNULL(NULLIF(:subCast, ''), p.subCast) "
//			+ "AND p.country = IFNULL(NULLIF(:country, ''), p.country) "
//			+ "AND p.townCity = IFNULL(NULLIF(:townCity, ''), p.townCity) "
//			+ "AND p.cast = IFNULL(NULLIF(:cast, ''), p.cast)"
//			+ "AND pd.height BETWEEN IFNULL(:heightFrom, pd.height) AND IFNULL(:heightTo, pd.height)")
//	List<Object[]> findUserProfile(@Param("religion") String religion, @Param("birthPlace") String birthPlace,
//			@Param("marriageStatus") String marriageStatus, @Param("motherTongue") String motherTongue,
//			@Param("state") String state, @Param("subCast") String subCast,
//			@Param("country") String country, @Param("townCity") String townCity, @Param("cast") String cast,@Param("heightFrom") Integer heightFrom,
//			@Param("heightTo") Integer heightTo);
//	
//	//Query for joining all tables 
//	@Query("SELECT p, u, pd  FROM Profile p " + "LEFT JOIN p.user u " + "LEFT JOIN p.profileDetails pd ")
//	List<Object[]> findProfilesWithDetails();
//	
//	
//	//Query for AdvanceSerach api 
//	@Query("SELECT u.firstName, u.middleName, u.lastName, u.emailId, "
//	        + "p.country, p.address1, p.address2, p.bloodGroup, p.cast, p.dob, p.profileName, p.religion, p.state, p.subCast, p.townCity, "
//	        + "pd.height, pd.weight, pd.birthPlace, pd.education, pd.company, pd.eatingHabits, pd.smoking, pd.drinking, pd.mangalik, pd.gothram, pd.zodiac, p.bloodGroup, pd.collegeUniversity, pd.company, pd.marriageStatus, pd.motherTongue "
//	        + "FROM User u "
//	        + "INNER JOIN u.profile p "
//	        + "INNER JOIN p.profileDetails pd "
//	        + "WHERE pd.education = IFNULL(NULLIF(:education,''),pd.education) "
//	        + "AND pd.company = IFNULL(NULLIF(:company,''), pd.company) "
//	        + "AND pd.eatingHabits = IFNULL(NULLIF(:eatingHabits,''), pd.eatingHabits) "
//	        + "AND pd.smoking = IFNULL(NULLIF(:smoking,''), pd.smoking) "
//	        + "AND pd.drinking = IFNULL(NULLIF(:drinking,''),pd.drinking) "
//	        + "AND pd.gothram = IFNULL(NULLIF(:gothram,''), pd.gothram) "
//	        + "AND pd.zodiac = IFNULL(NULLIF(:zodiac,''), pd.zodiac) "
//	        + "AND p.bloodGroup = IFNULL(NULLIF(:bloodGroup,''), p.bloodGroup)")
//	List<Object[]> searchProfiles(
//	        @Param("education") String education,
//	        @Param("company") String company,
//	        @Param("eatingHabits") String eatingHabits,
//	        @Param("smoking") String smoking,
//	        @Param("drinking") String drinking,
//	        @Param("gothram") String gothram,
//	        @Param("zodiac") String zodiac,
//	        @Param("bloodGroup") String bloodGroup);
//
//	
//	    @Query("SELECT u.firstName, u.middleName, u.lastName, u.emailId, "
//		        + "p.country, p.address1, p.address2, p.bloodGroup, p.cast, p.dob, p.profileName, p.religion, p.state, p.subCast, p.townCity, "
//		        + "pd.height, pd.weight, pd.birthPlace, pd.education, pd.company, pd.eatingHabits, pd.smoking, pd.drinking, pd.mangalik, pd.gothram, pd.zodiac, p.bloodGroup, pd.collegeUniversity, pd.company, pd.marriageStatus, pd.motherTongue "
//		        + "FROM User u "
//		        + "INNER JOIN u.profile p "
//		        + "INNER JOIN p.profileDetails pd "
//	            + "WHERE (:education IS NULL OR LOWER(pd.education) = LOWER(:education)) "
//	            + "AND (:company IS NULL OR LOWER(pd.company) = LOWER(:company)) "
//	            + "AND (:eatingHabits IS NULL OR LOWER(pd.eatingHabits) = LOWER(:eatingHabits)) "
//	            + "AND (:smoking IS NULL OR LOWER(pd.smoking) = LOWER(:smoking)) "
//	            + "AND (:drinking IS NULL OR LOWER(pd.drinking) = LOWER(:drinking)) "
//	            + "AND (:gothram IS NULL OR LOWER(pd.gothram) = LOWER(:gothram)) "
//	            + "AND (:zodiac IS NULL OR LOWER(pd.zodiac) = LOWER(:zodiac)) "
//	            + "AND (:bloodGroup IS NULL OR LOWER(p.bloodGroup) = LOWER(:bloodGroup))")
//	    List<Object[]>findProfileByFilter(@Param("education") String education,
//	                                      @Param("company") String company,
//	                                      @Param("eatingHabits") String eatingHabits,
//	                                      @Param("smoking") String smoking,
//	                                      @Param("drinking") String drinking,
//	                                      @Param("gothram") String gothram,
//	                                      @Param("zodiac") String zodiac,
//	                                      @Param("bloodGroup") String bloodGroup);
//	    
//	    
//	
//
}
