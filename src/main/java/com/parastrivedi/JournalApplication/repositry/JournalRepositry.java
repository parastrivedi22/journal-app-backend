package com.parastrivedi.JournalApplication.repositry;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.parastrivedi.JournalApplication.entity.Journal;

public interface JournalRepositry extends MongoRepository<Journal, ObjectId> {

}
