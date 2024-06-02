package com.parastrivedi.JournalApplication.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.parastrivedi.JournalApplication.entity.Journal;
import com.parastrivedi.JournalApplication.exception.ApiResponse;

public interface JournalService {
	public List<Journal> getAll();

	public List<Journal> getAllUserJournals(String userEmail);

	public Journal getById(ObjectId id);

	public ApiResponse deleteById(ObjectId journalId);

	public Journal createJournal(String userEmail, Journal journal);

	public Journal updateJournal(ObjectId journalId, Journal newJournal);
}
