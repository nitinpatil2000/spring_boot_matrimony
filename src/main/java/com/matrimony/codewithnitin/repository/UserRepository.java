package com.matrimony.codewithnitin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matrimony.codewithnitin.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	  Optional<User> findByEmailId(String emailId);

	  Optional<User> findByMobileNo(String mobileNo);
	  
	  public User findByUserId(Integer userId);
	  
//	  public User findById(String userId);

	}
