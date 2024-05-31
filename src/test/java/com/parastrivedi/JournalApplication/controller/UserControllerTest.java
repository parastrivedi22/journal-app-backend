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
	public void testGetAllUsers() {
		ResponseEntity<List<User>> allUsers = userController.getAllUsers();
		assertNotNull(allUsers);

	}

	ObjectId id = new ObjectId("664f8195d29e4129831d658a");

	@Test
	public void testegGUserById() {
		User u = new User();

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

	@Test
	public void testUpdateUser() {
		User u1 = new User();
		User u2 = new User();

		u1.setId(id);
		u1.setPassword("hlo");
		u1.setRole("adf");
		u1.setUserName("adfda");
		u1.setUserEmail("adfalk@gmail.com");

		u2.setId(id);
		u2.setPassword("hello");
		u2.setRole("USER");
		u2.setUserName("Paras");
		u2.setUserEmail("paras@gmail.com");

		when(userController.updateUser(any(), any()).getBody()).thenReturn(u2);
		User body = userController.updateUser(id, u1).getBody();

		assertNotNull(body);
		assertEquals(u2, body);
	}

}