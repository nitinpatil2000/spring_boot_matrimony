package com.matrimony.codewithnitin.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.codewithnitin.payload.RegisterRequest;
import com.matrimony.codewithnitin.service.RegisterService;



@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class ViewListController {
	
	@Autowired
	private RegisterService registerService;
	
	//GET - PROFILE GET
		@GetMapping("/viewlist")
		public ResponseEntity<List<RegisterRequest>> getAllUsers(){
			List<RegisterRequest> userList = this.registerService.getAllUsers();
			return ResponseEntity.ok(userList);
		}

}

