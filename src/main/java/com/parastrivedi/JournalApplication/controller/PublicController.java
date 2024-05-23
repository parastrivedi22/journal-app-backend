package com.parastrivedi.JournalApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parastrivedi.JournalApplication.entity.Journal;
import com.parastrivedi.JournalApplication.entity.User;
import com.parastrivedi.JournalApplication.exception.ApiResponse;
import com.parastrivedi.JournalApplication.service.JournalService;
import com.parastrivedi.JournalApplication.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
	@Autowired
	private UserService userService;


	@Autowired
	private JournalService journalService;
	
	

	@PostMapping("/u")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		try {
			return new ResponseEntity<>(userService.newUser(user), HttpStatus.CREATED);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return new ResponseEntity<>(new ApiResponse("User has already present ", false), HttpStatus.BAD_REQUEST);
	}
	
	
	
	@GetMapping("/j")
	public ResponseEntity<List<Journal>> getAllJournalEntities(){
		return new ResponseEntity<>(journalService.getAll(),HttpStatus.OK);
	}
}
