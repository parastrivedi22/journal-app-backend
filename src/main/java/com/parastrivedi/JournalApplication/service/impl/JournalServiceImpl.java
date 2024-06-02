package com.parastrivedi.JournalApplication.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parastrivedi.JournalApplication.entity.Journal;
import com.parastrivedi.JournalApplication.entity.User;
import com.parastrivedi.JournalApplication.exception.ApiResponse;
import com.parastrivedi.JournalApplication.exception.ResourceNotFoundException;
import com.parastrivedi.JournalApplication.repositry.JournalRepository;
import com.parastrivedi.JournalApplication.repositry.UserRepository;
import com.parastrivedi.JournalApplication.service.JournalService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JournalServiceImpl implements JournalService {

	@Autowired
	private JournalRepository journalRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Journal> getAll() {

		return journalRepository.findAll();

	}

	@Override
	public List<Journal> getAllUserJournals(String userEmail) {
		log.info("{} method getAllUserJournals", JournalServiceImpl.class);
		User user = userRepository.findByUserEmail(userEmail)
				.orElseThrow(() -> new ResourceNotFoundException("User", userEmail));
		return user.getJournalEntries();
	}

	@Override
	public Journal getById(ObjectId id) {
		log.info("{} method getById", JournalServiceImpl.class);
		return journalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("journal", id));
	}

	@Override
	@Transactional
	public ApiResponse deleteById(ObjectId journalId) {
		log.info("{} method deleteById", JournalServiceImpl.class);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();

		User user = userRepository.findByUserEmail(userEmail)
				.orElseThrow(() -> new ResourceNotFoundException("User", userEmail));

		List<Journal> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(journalId))
				.collect(Collectors.toList());

		if (!collect.isEmpty()) {
			boolean removed = user.getJournalEntries().removeIf(e -> e.getId().equals(journalId));
			if (removed == true) {
				userRepository.save(user);
				journalRepository.deleteById(journalId);
				return new ApiResponse("Journal deleted", true);
			}
		}
		return new ApiResponse("Journal is no longer avaible", false);
	}

	@Override
	@Transactional
	public Journal createJournal(String userEmail, Journal journal) {
		log.info("{} method createJournal", JournalServiceImpl.class);
		User user = userRepository.findByUserEmail(userEmail)
				.orElseThrow(() -> new ResourceNotFoundException("User", userEmail));

		journal.setCreatedAt(LocalDateTime.now());
		Journal save = journalRepository.save(journal);
		user.getJournalEntries().add(save);
		userRepository.save(user);
		return journal;
	}

	@Transactional
	public Journal updateJournal(ObjectId journalId, Journal newEntry) {

		log.info("{} method updateJournal", JournalServiceImpl.class);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();

		User user = userRepository.findByUserEmail(userEmail)
				.orElseThrow(() -> new ResourceNotFoundException("User", userEmail));

		List<Journal> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(journalId))
				.collect(Collectors.toList());

		if (!collect.isEmpty()) {
			Optional<Journal> journalEntry = journalRepository.findById(journalId);
			if (journalEntry.isPresent()) {
				Journal old = journalEntry.get();
				old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().trim().equals("")
						? newEntry.getTitle().trim()
						: old.getTitle());
				old.setContent(newEntry.getContent() != null && !newEntry.getContent().trim().equals("")
						? newEntry.getContent().trim()
						: old.getContent());
				journalRepository.save(old);
				return old;
			}
		}
		return null;
	}

}
