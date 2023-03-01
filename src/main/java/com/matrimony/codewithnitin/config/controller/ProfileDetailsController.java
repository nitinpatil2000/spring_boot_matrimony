package com.matrimony.codewithnitin.config.controller;

import java.util.List;

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
import com.matrimony.codewithnitin.entity.User;
import com.matrimony.codewithnitin.payload.ApiResponce;
import com.matrimony.codewithnitin.payload.ProfileDetailsResponce;
import com.matrimony.codewithnitin.payload.ProfileResponce;
import com.matrimony.codewithnitin.repository.ProfileDetailsRepository;
import com.matrimony.codewithnitin.repository.ProfileRepository;
import com.matrimony.codewithnitin.service.ProfileDetailsService;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class ProfileDetailsController {

	@Autowired
	ProfileDetailsRepository profileDetailsRepository;
	
	@Autowired
	public ProfileDetailsService profileDetailsService;
	

	@Autowired
	ProfileRepository profileRepository;
	
	
	@Autowired
	AuthenticationController authenticationController;


	
	@GetMapping("/profiledetails")
	public List<ProfileDetails> getData() {
		return profileDetailsService.getData();
	}
	
	@PutMapping("/updatedetails")
	public ProfileDetails update(@RequestBody ProfileDetails profileDetails) {
		ProfileDetails save = profileDetailsRepository.save(profileDetails);
		return save;
	}
	
	@DeleteMapping("/profiledetails/{profileDetailsId}")
	public ResponseEntity<ApiResponce> deleteProfileDetails(@PathVariable("profileDetailsId") Integer profileDetailsId)
	{
	this.profileDetailsService.deleteProfileDetails(profileDetailsId);
	return new ResponseEntity<ApiResponce>(new ApiResponce("User Deleted Successfully", true,100), HttpStatus.OK);
	}
	

	
	@GetMapping("/profiledetails/{profileDetailsId}")
	public ResponseEntity<ProfileDetails> getSingleProfile(@PathVariable Integer profileDetailsId){
		ProfileDetails profileDetails = this.profileDetailsService.getSingleProfile(profileDetailsId);
		return new ResponseEntity<ProfileDetails>(profileDetails, HttpStatus.OK);
	}

	
	
	
	@PostMapping("/createprofiledetails")
	public ResponseEntity<ProfileDetailsResponce> createProfile(@RequestBody ProfileDetails profileDetails) {
	    try {
	        Integer profileId = profileDetails.getProfile().getProfileId();
	        Profile profile = profileRepository.findById(profileId)
	                .orElseThrow(() -> new IllegalArgumentException("profile  not found for the given profileId"));
	        profileDetails.setProfile(profile);
	        ProfileDetails savedProfile = profileDetailsRepository.save(profileDetails);
	        ProfileDetailsResponce profileDetailsResponce = new ProfileDetailsResponce(savedProfile.getProfileDetailsId(), "Profile Details created successfully");
	        return ResponseEntity.ok(profileDetailsResponce);

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
	
	
	
//	@PostMapping("/createprofiledetails")
//	public ResponseEntity<?> createProfileDetail(@RequestBody ProfileDetails profileDetails) {
//		Integer profileId = profileDetails.getProfile().getProfileId();
//		Optional<Profile> optionalProfile = profileRepository.findById(profileId);
//		if (optionalProfile.isPresent()) {
//			Profile existingProfile = optionalProfile.get();
//			profileDetails.setProfile(existingProfile);
//			ProfileDetails savedProfileDetails = profileDetailsRepository.save(profileDetails);
//			if (savedProfileDetails != null) {
//				return ResponseEntity
//						.ok("ProfileDetails created successfully for existing Profile with ID " + profileId);
//			} else {
//				return ResponseEntity.badRequest()
//						.body("Failed to create ProfileDetails for existing Profile with ID " + profileId);
//			}
//		} else {
//			Profile newProfile = profileRepository.save(profileDetails.getProfile());
//			profileDetails.setProfile(newProfile);
//			ProfileDetails savedProfileDetails = profileDetailsRepository.save(profileDetails);
//			if (savedProfileDetails != null) {
//				return ResponseEntity
//						.ok("New Profile and ProfileDetails created successfully with ID " + newProfile.getProfileId());
//			} else {
//				return ResponseEntity.badRequest().body("Failed to create new Profile and ProfileDetails");
//			}
//		}
	}


	

