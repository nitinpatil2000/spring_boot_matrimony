package com.matrimony.codewithnitin.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.codewithnitin.entity.PartnerPreferance;
import com.matrimony.codewithnitin.entity.Profile;
import com.matrimony.codewithnitin.payload.ApiResponce;
import com.matrimony.codewithnitin.payload.PartnerPreferenceResponce;
import com.matrimony.codewithnitin.repository.PartnerPreferenceRepository;
import com.matrimony.codewithnitin.repository.ProfileRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PartnerPreferenceController {

	
	@Autowired
	PartnerPreferenceRepository partnerPreferenceRepository;
	
	@Autowired
	ProfileRepository profileRepository;
	
	
//	@PostMapping("/createpartnerPreferance")s
//	public PartnerPreferance create(@RequestBody PartnerPreferance partnerPreferance) {
//		return partnerPreferenceRepository.save(partnerPreferance);
//	}
	
	
	@PostMapping("/createpartnerpreferance")
	public ResponseEntity<PartnerPreferenceResponce> createProfile(@RequestBody PartnerPreferance partnerPreferance) {
	    try {
	        Integer profileId = partnerPreferance.getProfile().getProfileId();
	        Profile profile = profileRepository.findById(profileId)
	                .orElseThrow(() -> new IllegalArgumentException("profile details not found for the given profileId"));
	        partnerPreferance.setProfile(profile);
	        PartnerPreferance savedProfile = partnerPreferenceRepository.save(partnerPreferance);
	        PartnerPreferenceResponce partnerPreferenceResponce = new PartnerPreferenceResponce(savedProfile.getPreferenceId(), "Profile Preference created successfully");
	        return ResponseEntity.ok(partnerPreferenceResponce);

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

}
