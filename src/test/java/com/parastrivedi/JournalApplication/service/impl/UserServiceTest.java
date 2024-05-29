package com.parastrivedi.JournalApplication.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.parastrivedi.JournalApplication.entity.User;
import com.parastrivedi.JournalApplication.repositry.UserRepository;


	@ExtendWith(MockitoExtension.class)
	public class UserServiceTest {

	    @Mock
	    private UserRepository userRepository;

	    @Mock
	    private PasswordEncoder passwordEncoder;

	    @InjectMocks
	    private UserServiceImpl userServiceImpl;

	    private User user;

	    @BeforeEach
	    void setUp() {
	        user = new User();
	        user.setPassword("plainPassword");
	    }

	    @Test
	    void testNewUser() {
	        // Arrange
	        String encodedPassword = "encodedPassword";
	        when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);
	        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

	        // Act
	        User savedUser = userServiceImpl.newUser(user);

	        // Assert
	        assertEquals(encodedPassword, savedUser.getPassword());
	    }
	}
