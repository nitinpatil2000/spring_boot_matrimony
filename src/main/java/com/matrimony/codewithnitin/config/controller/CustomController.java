//package com.matrimony.codewithnitin.config.controller;
//
//import java.util.Optional;
//
//import org.hibernate.engine.spi.ExtendedSelfDirtinessTracker;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.matrimony.codewithnitin.entity.ExtendedProfileFamily;
//import com.matrimony.codewithnitin.entity.PartnerPreferance;
//import com.matrimony.codewithnitin.entity.Profile;
//import com.matrimony.codewithnitin.entity.ProfileDetails;
//import com.matrimony.codewithnitin.entity.ProfileFamily;
//import com.matrimony.codewithnitin.entity.User;
//import com.matrimony.codewithnitin.payload.CustomProfile;
//import com.matrimony.codewithnitin.repository.ExtendedProfileFamilyRepository;
//import com.matrimony.codewithnitin.repository.PartnerPreferenceRepository;
//import com.matrimony.codewithnitin.repository.ProfileDetailsRepository;
//import com.matrimony.codewithnitin.repository.ProfileFamilyRepository;
//import com.matrimony.codewithnitin.repository.ProfileRepository;
//import com.matrimony.codewithnitin.repository.UserRepository;
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = "*")
//public class CustomController {
//	
//	@Autowired
//	UserRepository userRepository;
//	
//	@Autowired
//	ProfileRepository profileRepository;
//	
//	@Autowired
//	ProfileDetailsRepository profileDetailsRepository;
//	
//	@Autowired
//	ProfileFamilyRepository profileFamilyRepository;
//	
//	@Autowired
//	ExtendedProfileFamilyRepository extendedProfileFamilyRepository;
//	
//	
//	@Autowired
//	PartnerPreferenceRepository partnerPreferenceRepository;
//	
//	
//	
//	// Getapi to get adata from all tables -show all details of user registered
//			@GetMapping("/customprofile/{id}")
//			public CustomProfile getCustomProfileById(@PathVariable Integer id) {
//				ProfileDetails profileDetails = profileDetailsRepository.findById(id)
//						.orElseThrow(() -> new RuntimeException("ProfileDetails not found"));
//				Optional<User> user = userRepository.findById(id);
////							.orElseThrow(() -> new RuntimeException("User not found"));
//				Profile profile = profileRepository.findById(id)
//						.orElseThrow(() -> new RuntimeException("Profile not found"));
//				ProfileFamily profileFamily = profileFamilyRepository.findById(id)
//						.orElseThrow(() -> new RuntimeException("Profile not found"));
//				ExtendedProfileFamily extendedProfileFamily = extendedProfileFamilyRepository.findById(id)
//						.orElseThrow(() -> new RuntimeException("Profile not found"));
//				PartnerPreferance partnerPreferance = partnerPreferenceRepository.findById(id)
//						.orElseThrow(() -> new RuntimeException("Profile not found"));
//				// List<ProfileFamily> family = profileFamilyRepository.findByProfileId(id);
//				
//			    return new CustomProfile(profile, profileDetails, profileFamily, extendedProfileFamily, partnerPreferance, user);
//			}		
//
//}
