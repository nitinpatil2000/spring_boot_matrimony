package com.matrimony.codewithnitin.config.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.codewithnitin.entity.Profile;
import com.matrimony.codewithnitin.entity.ProfileDetails;
import com.matrimony.codewithnitin.entity.ProfileFamily;
import com.matrimony.codewithnitin.payload.ApiResponce;
import com.matrimony.codewithnitin.payload.ProfileDetailsResponce;
import com.matrimony.codewithnitin.payload.ProfileFamilyResponce;
import com.matrimony.codewithnitin.repository.ProfileFamilyRepository;
import com.matrimony.codewithnitin.repository.ProfileRepository;
import com.matrimony.codewithnitin.service.ProfileFamilyService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProfileFamilyController {

	
	@Autowired
	ProfileFamilyRepository profileFamilyRepository;
	
	@Autowired
	ProfileFamilyService profileFamilyService;

	@Autowired
	ProfileRepository profileRepository;
	

//	@PostMapping("/createprofilefamily")
//	public ProfileFamily create(@RequestBody ProfileFamily profilFamily) {
//		return profileFamilyRepository.save(profilFamily);
//	}
	
	
	
	@PostMapping("/createprofilefamily")
	public ResponseEntity<ProfileFamilyResponce> createProfile(@RequestBody ProfileFamily profileFamily) {
	    try {
	        Integer profileId = profileFamily.getProfile().getProfileId();
	        Profile profile = profileRepository.findById(profileId)
	                .orElseThrow(() -> new IllegalArgumentException("profile  not found for the given profileId"));
	        profileFamily.setProfile(profile);
	        ProfileFamily savedProfile = profileFamilyRepository.save(profileFamily);
	        ProfileFamilyResponce profileFamilyResponce = new ProfileFamilyResponce(savedProfile.getProfileFamilyId(), "Profile Family created successfully");
	        return ResponseEntity.ok(profileFamilyResponce);

	    } catch (DataIntegrityViolationException ex) {
	        String message = "The data you're trying to insert violates a unique constraint. Please check your input and try again.";
	        ApiResponce response = new ApiResponce(message, false, HttpStatus.CONFLICT.value());
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);

	    } catch (Exception ex) {
	        String message = "An error occurred while processing your request.";
	        ApiResponce response = new ApiResponce(message, false, HttpStatus.INTERNAL_SERVER_ERROR.value()); 
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	
	

	
	@GetMapping("/profilefamily")
	public List<ProfileFamily> getData() {
		return profileFamilyService.getData();
	}
	
	
	
	@GetMapping("/profilefamily/{profileFamilyId}")
	public ResponseEntity<ProfileFamily> getSingleProfileFamily(@PathVariable Integer profileFamilyId){
		ProfileFamily profileFamily = this.profileFamilyService.getSingleProfileFamily(profileFamilyId);
		return new ResponseEntity<ProfileFamily>(profileFamily, HttpStatus.OK);
	}
	
	
	
	
	@PutMapping("/profilefamily")
	public ProfileFamily update(@RequestBody ProfileFamily profileFamily) {
		ProfileFamily save = profileFamilyRepository.save(profileFamily);
		return save;
	}
	
	@DeleteMapping("/profilefamily/{profileFamilyId}")
	public ResponseEntity<ApiResponce> deleteProfileDetails(@PathVariable("profileFamilyId") Integer profileFamilyId)
	{
	this.profileFamilyService.deleteProfileFamily(profileFamilyId);
	return new ResponseEntity<ApiResponce>(new ApiResponce("User Deleted Successfully", true,100), HttpStatus.OK);
	}
}
