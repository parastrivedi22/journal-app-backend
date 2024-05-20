package com.parastrivedi.JournalApplication.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.parastrivedi.JournalApplication.entity.Journal;
import com.parastrivedi.JournalApplication.exception.ApiResponse;

public interface JournalService {
		public List<Journal>getAll();
		public Journal getById(ObjectId id);
		public ApiResponse deleteById(ObjectId id);
		public Journal createJournal(Journal journal);
		public Journal updateJournal(ObjectId id, Journal newJournal);
}
