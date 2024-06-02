package com.parastrivedi.JournalApplication.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.parastrivedi.JournalApplication.entity.User;
import com.parastrivedi.JournalApplication.exception.ApiResponse;
import com.parastrivedi.JournalApplication.exception.ResourceNotFoundException;
import com.parastrivedi.JournalApplication.repositry.UserRepository;
import com.parastrivedi.JournalApplication.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder encorder;

	@Override
	public User newUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(encorder.encode(user.getPassword()));
		log.info("{} method : newUser called  ", UserServiceImpl.class);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		log.info("{} method getAllUser called", UserServiceImpl.class);
		return userRepository.findAll();
	}

	@Override
	public User getUserById(ObjectId id) {
		// TODO Auto-generated method stub
		log.info("{} method getUserById called", UserServiceImpl.class);
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", id));
	}

	@Override
	public ApiResponse deleteById(ObjectId id) {
		// TODO Auto-generated method stub
		log.info("{} method deleteById called", UserServiceImpl.class);
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", id));
		userRepository.delete(user);
		return new ApiResponse("User deleted", true);
	}

	@Override
	public User updateUser(ObjectId id, User newUser) {
		// TODO Auto-generated method stub
		log.info("{} method updateUser called", UserServiceImpl.class);
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", id));
		user.setUserName(newUser.getUserName() == null || newUser.getUserName().trim().equals("") ? user.getUserName()
				: newUser.getUserName());

		user.setPassword(newUser.getPassword() == null || newUser.getPassword().trim().equals("") ? user.getPassword()
				: newUser.getPassword());
		return userRepository.save(user);

	}

}
