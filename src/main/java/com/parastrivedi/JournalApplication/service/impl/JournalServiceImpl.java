package com.parastrivedi.JournalApplication.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parastrivedi.JournalApplication.entity.Journal;
import com.parastrivedi.JournalApplication.entity.User;
import com.parastrivedi.JournalApplication.exception.ApiResponse;
import com.parastrivedi.JournalApplication.exception.ResourceNotFoundException;
import com.parastrivedi.JournalApplication.repositry.JournalRepository;
import com.parastrivedi.JournalApplication.repositry.UserRepository;
import com.parastrivedi.JournalApplication.service.JournalService;

@Service
public class JournalServiceImpl implements JournalService {

	@Autowired
	private JournalRepository journalRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Journal> getAll() {
		// TODO Auto-generated method stub
		return journalRepository.findAll();

	}
	
	
	@Override
	public List<Journal> getAllUserJournals(ObjectId userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("journal", userId));
		return user.getJournalEntries() ;
	}

	@Override
	public Journal getById(ObjectId id) {
		// TODO Auto-generated method stub
		return journalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("journal", id));

	}

	@Override
	@Transactional
	public ApiResponse deleteById(ObjectId userId, ObjectId journalId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
		journalRepository.findById(journalId).orElseThrow(() -> new ResourceNotFoundException("journal", journalId));
		user.getJournalEntries().removeIf(e->e.getId().equals(journalId));
		userRepository.save(user);
		journalRepository.deleteById(journalId);
		return new ApiResponse("Journal deleted", true);
	}

	@Override
	@Transactional
	public Journal createJournal(ObjectId userId, Journal journal) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("journal", userId));
		journal.setCreatedAt(LocalDateTime.now());
		Journal save = journalRepository.save(journal);
		user.getJournalEntries().add(save);
		userRepository.save(user);
		return journal;
	}

	public Journal updateJournal(ObjectId id, Journal newJournal) {

		Journal journal = journalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("journal", id));

		journal.setTitle(newJournal.getTitle() == null || newJournal.getTitle().trim().equals("") ? journal.getTitle()
				: newJournal.getTitle().trim());

		journal.setContent(
				newJournal.getContent() == null || newJournal.getContent().trim().equals("") ? journal.getContent()
						: newJournal.getContent().trim());

		Journal updatedJournal = journalRepository.save(journal);
		return updatedJournal;
	}

	

}
