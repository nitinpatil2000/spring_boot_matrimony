package com.matrimony.codewithnitin.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProfileDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer profileDetailsId;
	
	@NotNull
	private String skinColour;
	
	@NotNull
	private String height;
	
	@NotNull
	private String weight;
	
	@NotNull
	private String bodyType;
	
	@NotNull
	private String education;
	
	@NotNull
	private String collegeUniversity;
	
	@NotNull
	private String occupation;
	
	@NotNull
	private String profession;
	
	@NotNull
	private String company;
	
	@NotNull
	private String annualIncome;
	
	@NotNull
	private String nativePlace;
//	private String birthPlace;
	
	@NotNull
	private String birthTime;
	
	@NotNull
	private String marriageStatus;
	
	@NotNull
	private String physicalStatus;
	
	@NotNull
	private String motherTongue;
	
	@NotNull
	private String zodiac;
	
	@NotNull
	private String eatingHabits;
	
	@NotNull
	private String drinking;
	
	@NotNull
	private String smoking;
	
	@NotNull
	private String starNakshtra;
	
	@NotNull
	private String hobbies;
	
	@NotNull
	private String gothram;
	
	@NotNull
	private String mangalik;
	
	@NotNull
	private String propertyDetail;


	
	 @CreationTimestamp
	  private LocalDateTime dateCreated;
		
	  @UpdateTimestamp
	  private LocalDateTime dateUpdated;
	

	// mapping for profile- profiledetails
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "profile_id", referencedColumnName = "profileId")
	private Profile profile;
}
