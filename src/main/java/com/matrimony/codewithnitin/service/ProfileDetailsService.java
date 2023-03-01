package com.matrimony.codewithnitin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.codewithnitin.entity.ProfileDetails;
import com.matrimony.codewithnitin.exception.ResourceNotFoundException;
import com.matrimony.codewithnitin.repository.ProfileDetailsRepository;



@Service
public class ProfileDetailsService {
	@Autowired
	ProfileDetailsRepository profileDetailsRepository;

	
	public void saveProfileDetails(ProfileDetails profileDetails) {
		profileDetailsRepository.save(profileDetails);
	      
	}
	
	
	public List<ProfileDetails> getData(){
		return profileDetailsRepository.findAll();
	}
	



	public ProfileDetails getSingleProfile(Integer profileDetailsId) {
		ProfileDetails profileDetails = this.profileDetailsRepository.findById(profileDetailsId)
				.orElseThrow(()->new ResourceNotFoundException("profileDetails", "profileDetails id", profileDetailsId));
					return profileDetails;
	}
	
	
	public void deleteProfileDetails(Integer profileDetailsId) {
		ProfileDetails profileDetails =  this.profileDetailsRepository.findById(profileDetailsId).orElseThrow(()->new ResourceNotFoundException("profileDetails","profileDetails id", profileDetailsId));
		this.profileDetailsRepository.delete(profileDetails);		

	}
}
