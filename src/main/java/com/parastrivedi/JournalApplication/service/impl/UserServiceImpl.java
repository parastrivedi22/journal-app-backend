package com.parastrivedi.JournalApplication.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parastrivedi.JournalApplication.entity.User;
import com.parastrivedi.JournalApplication.exception.ApiResponse;
import com.parastrivedi.JournalApplication.exception.ResourceNotFoundException;
import com.parastrivedi.JournalApplication.repositry.UserRepository;
import com.parastrivedi.JournalApplication.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User newUser(User user) {
		// TODO Auto-generated method stub
				
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUserById(ObjectId id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", id));
	}

	@Override
	public ApiResponse deleteById(ObjectId id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", id));
		userRepository.delete(user);
		return new ApiResponse("User deleted", true);
	}

	@Override
	public User updateUser(ObjectId id, User newUser) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", id));
		user.setUserName(
				newUser.getUserName()== null || newUser.getUserName().trim().equals("")? user.getUserName() : newUser.getUserName());
		
		user.setPassword(
				newUser.getPassword()==null || newUser.getPassword().trim().equals("")? user.getPassword() : newUser.getPassword());
		 return  userRepository.save(user);
		
	
	}

}
