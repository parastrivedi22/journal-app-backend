package com.parastrivedi.JournalApplication.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parastrivedi.JournalApplication.entity.User;
import com.parastrivedi.JournalApplication.exception.ApiResponse;
import com.parastrivedi.JournalApplication.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable ObjectId id){
		return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user){
		return new ResponseEntity<>(userService.newUser(user), HttpStatus.CREATED);
//		try {
//			return new ResponseEntity<>(userService.newUser(user), HttpStatus.CREATED);
//		}catch(Exception ex) {
//			System.out.println(ex);
//		}
//		return new ResponseEntity<>(new ApiResponse("User has already present ", false), HttpStatus.BAD_REQUEST);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable ObjectId id){
		return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable ObjectId id, @RequestBody User newUser){
		
		return new ResponseEntity<>(userService.updateUser(id, newUser), HttpStatus.OK);
		
	}

}
