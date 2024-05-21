package com.parastrivedi.JournalApplication.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.parastrivedi.JournalApplication.entity.User;
import com.parastrivedi.JournalApplication.exception.ApiResponse;

public interface UserService {
	public User newUser(User user);
	public List<User> getAllUser();
	public User getUserById(ObjectId id);
	public ApiResponse deleteById(ObjectId id);
	public User updateUser(ObjectId id, User newUser);

}
