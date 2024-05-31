package com.parastrivedi.JournalApplication.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.parastrivedi.JournalApplication.entity.User;
import com.parastrivedi.JournalApplication.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserService userService;
	
	@Test
	public void test() {
		ResponseEntity<List<User>> allUsers = userController.getAllUsers();
		assertNotNull(allUsers);

	}
	
	ObjectId id = new ObjectId("664f8195d29e4129831d658a");
	
	@Test
	public void test2() {
		User u  = new User();
		
		u.setId(id);
		u.setPassword("hello");
		u.setRole("USER");
		u.setUserName("Paras");
		u.setUserEmail("paras@gmail.com");
		when(userController.getUserById(any()).getBody()).thenReturn(u);
		User user = userController.getUserById(id).getBody();
		assertEquals(u, user);
		assertNotNull(user);
		
	}
	
}

