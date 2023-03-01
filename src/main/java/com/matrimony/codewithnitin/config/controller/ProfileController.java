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
import com.matrimony.codewithnitin.entity.User;
import com.matrimony.codewithnitin.payload.ApiResponce;
import com.matrimony.codewithnitin.payload.ProfileResponce;
import com.matrimony.codewithnitin.repository.ProfileRepository;
import com.matrimony.codewithnitin.repository.UserRepository;
import com.matrimony.codewithnitin.service.ProfileService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProfileController {

	@Autowired
	ProfileService profileservice;

	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	UserRepository userRepository;

//	@Autowired
//	AuthenticationService authenticationService;
//
//	@Autowired
//	UserRepository userRepository;
	
//	@Autowired
//	UserProfileImpl userProfileImpl ;
	
	//to create profile of registered user
	
//	@PostMapping("/createprofile")
//	public Profile create(@RequestBody Profile profile) {
//		 return  profileRepository.save(profile);
//		 }
	

	
//	@PostMapping("/createprofile")
//	public ResponseEntity<Map<String, Object>> create(@RequestBody Profile profile) {
//		User user = userRepository.findById(profile.getUser().getUserId()).orElse(null);
//		if (user == null) {
//			throw new IllegalArgumentException("User not found for the given userId");
//		}
//		Role defaultRole = Role.USER;
//		user.setRole(defaultRole);
//		profile.setUser(user);
//		Profile savedProfile = profileRepository.save(profile);
//		Map<String, Object> response = new HashMap<>();
//		response.put("profile", savedProfile);
//		response.put("user", user);
//		return new ResponseEntity<>(response, HttpStatus.CREATED); 
//	}
	
	@PostMapping("/createprofile")
	public ResponseEntity<ProfileResponce> createProfile(@RequestBody Profile profile) {
	    try {
	        Integer userId = profile.getUser().getUserId();
	        User user = userRepository.findById(userId)
	                .orElseThrow(() -> new IllegalArgumentException("User not found for the given userId"));
	        profile.setUser(user);
	        Profile savedProfile = profileRepository.save(profile);
	        ProfileResponce profileResponce = new ProfileResponce(savedProfile.getProfileId(), "Profile created successfully");
	        return ResponseEntity.ok(profileResponce);

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


	
//	@PostMapping("/profile")
//	 public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
//	 User user = userRepository.findById(profile.getUser().getUserId());
//	 // .orElseThrow(() -> new EntityNotFoundException("User not found"));
//	 profile.setUser(user);
//	 Profile savedProfile = profileRepository.save(profile);
//	 return ResponseEntity.ok(savedProfile);
//	 }





	// find list of profile
	@GetMapping("/profile")
	public List<Profile> getData() {

		return profileservice.getData();
	}

	// find byId
//	@GetMapping("/profile/{profileId}")
//	public ResponseEntity<Profile> getUserById(@PathVariable Integer profileId) {
//		Profile profile = profileRepository.findById(profileId)
//				.orElseThrow(() -> new RuntimeException("Profile not found"));
//		return ResponseEntity.ok(profile);
//	}
	
	@GetMapping("/profile/{profileId}")
	public ResponseEntity<Profile> getSingleProfile(@PathVariable Integer profileId){
		Profile profiles = this.profileservice.getSingleProfile(profileId);
		return new ResponseEntity<Profile>(profiles, HttpStatus.OK);
	}

	// update details
	@PutMapping("/updateprofile")
	public Profile update(@RequestBody Profile profile) {
		Profile save = profileRepository.save(profile);
		return save;
	}

	// delete userprofile
//	@DeleteMapping("/deleteprofile/{profileId}")
//	public String delete(@PathVariable Integer profileId) {
//		Profile profile = profileRepository.findById(profileId)
//				.orElseThrow(() -> new RuntimeException("Profile notfound"));
//		profileRepository.delete(profile);
//		return "User succesfully Deleted...";
//	}
	
	@DeleteMapping("/profile/{profileId}")
	public ResponseEntity<ApiResponce> deleteProfile(@PathVariable("profileId") Integer profileId)
	{
	this.profileservice.deleteProfile(profileId);
	return new ResponseEntity<ApiResponce>(new ApiResponce("User Deleted Successfully", true,100), HttpStatus.OK);
	}

	
//	@GetMapping("/profilesearch")
//	public List<Profile> getProfilesByreligion(@RequestParam("religion") String religion) {
//		return profileRepository.findByreligion(religion);
//	}
//
//	@GetMapping("/profilesearchcast")
//	public List<Profile> getProfilesBycast(@RequestParam("cast") String cast) {
//		return profileRepository.findBycast(cast);
//	}
//
//	// final search basic---------
//	@PostMapping("/basicusersearch")
//	public List<Object[]> getUserProfiles
//			(@RequestParam(required = false, defaultValue = "") String religion,
//			@RequestParam(required = false, defaultValue = "") String birthPlace,
//			@RequestParam(required = false, defaultValue = "") String marriageStatus,
//			@RequestParam(required = false, defaultValue = "") String motherTongue,
//			@RequestParam(required = false, defaultValue = "") String state,
//			@RequestParam(required = false, defaultValue = "") String subCast,
//			@RequestParam(required = false, defaultValue = "") String country,
//			@RequestParam(required = false, defaultValue = "") String townCity,
//			@RequestParam(required = false, defaultValue = "") String cast,
//			@RequestParam(required = false, defaultValue = "") Integer heightFrom,
//			@RequestParam(required = false, defaultValue = "") Integer heightTo) {
//
//		return userprofileservice.getUserProfiles(religion, birthPlace, marriageStatus, motherTongue, state, subCast,country,townCity,
//				cast,heightFrom,heightTo);
//	}
//	
//	@PostMapping("/advanceusersearch")
//	public List<Object[]> getSearchProfiles
//			(@RequestParam(required = false, defaultValue = "") String education,
//			@RequestParam(required = false, defaultValue = "") String company,
//			@RequestParam(required = false, defaultValue = "") String eatingHabits,
//			@RequestParam(required = false, defaultValue = "") String smoking,
//			@RequestParam(required = false, defaultValue = "") String state,
//			@RequestParam(required = false, defaultValue = "") String drinking,
//			@RequestParam(required = false, defaultValue = "") String gothram ,
//			@RequestParam(required = false, defaultValue = "") String zodiac,
//			@RequestParam(required = false, defaultValue = "") String bloodGroup){
//
//		return userprofileservice.getSearchProfiles(education, company, eatingHabits, smoking, drinking, gothram,
//				zodiac, bloodGroup);
//	}
//	
//	
//	@PostMapping("/advancesearch")
//	    public List<Object[]> getProfiles(
//	            @RequestParam(required=false) String education,
//	            @RequestParam(required=false) String company,
//	            @RequestParam(required=false) String eatingHabits,
//	            @RequestParam(required=false) String smoking,
//	            @RequestParam(required=false) String drinking,
//	            @RequestParam(required=false) String gothram,
//	            @RequestParam(required=false) String zodiac,
//	            @RequestParam(required=false) String bloodGroup) {
//	        
//	        return userprofileservice.getProfilesByFilter(
//	                education, company, eatingHabits, smoking, 
//	                drinking, gothram, zodiac, bloodGroup);
//	    }
//	
//
//
//
//	
//	
//	
//	// fetch all user data
//
//	@GetMapping("/profiles/{id}")
//	public List<Object[]> getAllProfilesWithDetails() {
//		return profileRepository.findProfilesWithDetails();
//	}
//	
////	@GetMapping("/profiles")
////    public ResponseEntity<List<Object[]>> getUserBasicProfiles(
////            @RequestParam(required = false) String religion,
////            @RequestParam(required = false) String birthPlace,
////            @RequestParam(required = false) String marriageStatus,
////            @RequestParam(required = false) String motherTongue,
////            @RequestParam(required = false) String state,
////            @RequestParam(required = false) String subCast,
////            @RequestParam(required = false) String cast) {
////
////        List<Object[]> profiles = userProfileImpl.findUserBasicProfile(religion, birthPlace, marriageStatus, motherTongue, state, subCast, cast);
////        return ResponseEntity.ok(profiles);
////    }
//
//	 
//	
//	
}

	