package com.parastrivedi.JournalApplication.repositry;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.parastrivedi.JournalApplication.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {

	public Optional<User> findByUserEmail(String userEmail);
}
