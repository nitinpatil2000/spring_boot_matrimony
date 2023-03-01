package com.matrimony.codewithnitin.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import com.matrimony.codewithnitin.config.JwtService;
import com.matrimony.codewithnitin.entity.Role;
import com.matrimony.codewithnitin.entity.Token;
import com.matrimony.codewithnitin.entity.User;
import com.matrimony.codewithnitin.payload.ApiResponce;
import com.matrimony.codewithnitin.payload.AuthenticationRequest;
import com.matrimony.codewithnitin.payload.RegisterRequest;
import com.matrimony.codewithnitin.repository.TokenRepository;
import com.matrimony.codewithnitin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  
  
  public User register(RegisterRequest request) {
//	  try {
		  var user = User.builder()
        .firstName(request.getFirstName())
        .middleName(request.getMiddleName())
        .lastName(request.getLastName())
        .emailId(request.getEmailId())
        .displayName(request.getDisplayName())
        .password(passwordEncoder.encode(request.getPassword()))
        .mobileNo(request.getMobileNo())
        .role(Role.USER)
        .build();
    Optional<User> existingUserByEmail = repository.findByEmailId(request.getEmailId());
	if (existingUserByEmail.isPresent()) {
		throw new IllegalArgumentException("Email already exists");
			}
	Optional<User> existingUserByMobileNumber = repository.findByMobileNo(request.getMobileNo());
	 if (existingUserByMobileNumber.isPresent()) {
	     throw new IllegalArgumentException("Mobile number already exists");
	    }
    
	 return repository.save(user);
  }
//	 catch (Illegal?ArgumentException e) {
//	 }
//
//  }
//}


  
  
  
  public Map<String, String> authenticate(AuthenticationRequest request) {
	  try {
		 
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmailId(), request.getPassword()));
    var user = repository.findByEmailId(request.getEmailId())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var userToken = user.getToken();
    if (userToken == null) {
        userToken = new Token();
        userToken.setUser(user);
    }

    userToken.setToken(jwtToken);
    tokenRepository.save(userToken);
    user.setToken(userToken);
    repository.save(user);

    
    Map<String, String> response = new HashMap<>();
    response.put("message", "Welcome user, your login is successful!");
    response.put("token", jwtToken);
    response.put("userId", String.valueOf(user.getUserId()));
    return response;
}
catch (AuthenticationException e) {
	Map<String, String> responce = new HashMap<>();
	responce.put("message", "Invalid email and password");
	responce.put("status", "101");
    return responce;

	}
  }
}
  
//	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responce);
  
//  public Map<String, String> authenticate(AuthenticationRequest request) {
//	  try {
//	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//	        var user = repository.findByEmail(request.getEmail())
//	                .orElseThrow();
//	        var jwtToken = jwtService.generateToken(user);
//
//	        // Create a new Token entity and associate it with the user
//	        var token = new Token(jwtToken, user);
//	        tokenRepository.save(token);
//
//	        Map<String, String> response = new HashMap<>();
//	        response.put("message", "Welcome user, your login is successful!");
//	        response.put("userId", user.getUserId().toString());
//	        response.put("token", jwtToken);
//	        return response;
//	    } catch (AuthenticationException e) {
//	        Map<String, String> response = new HashMap<>();
//	        response.put("message", "Invalid email and password");
//	        return response;
//	    }
//  }
  
