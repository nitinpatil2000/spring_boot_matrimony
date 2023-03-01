package com.matrimony.codewithnitin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.matrimony.codewithnitin.payload.RegisterRequest;


@Service
public interface RegisterService {

	RegisterRequest updateUser(RegisterRequest registerRequest, Integer userId);
	
	public void deleteUser(Integer userId);
	
	RegisterRequest getSingleUser(Integer userId);
	
	List<RegisterRequest> getAllUsers();
	
}
