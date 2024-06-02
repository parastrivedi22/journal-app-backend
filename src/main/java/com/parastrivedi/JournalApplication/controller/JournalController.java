package com.parastrivedi.JournalApplication.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parastrivedi.JournalApplication.entity.Journal;
import com.parastrivedi.JournalApplication.exception.ApiResponse;
import com.parastrivedi.JournalApplication.service.JournalService;

@RestController
@RequestMapping("/journal")
public class JournalController {

	@Autowired
	private JournalService journalService;

	@GetMapping
	public ResponseEntity<?> getAllJournalEntriesOfUser() {
		Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authenticated.getName();
		List<Journal> journals = journalService.getAllUserJournals(userEmail);

		if (journals != null && !journals.isEmpty()) {
			return new ResponseEntity<>(journals, HttpStatus.OK);
		}
		return new ResponseEntity<>(new ApiResponse("Journal list is empty", false), HttpStatus.NOT_FOUND);

	}

	@PostMapping
	public ResponseEntity<Journal> createJournalEntry(@RequestBody Journal journal) {
		Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authenticated.getName();

		return new ResponseEntity<>(journalService.createJournal(userEmail, journal), HttpStatus.CREATED);
	}

	@DeleteMapping("/{journalId}")
	public ResponseEntity<ApiResponse> deleteJournalEntry(@PathVariable ObjectId journalId) {

		return new ResponseEntity<>(journalService.deleteById(journalId), HttpStatus.OK);
	}

	@PutMapping("/{journalId}")
	public ResponseEntity<?> updateJournalEntity(@PathVariable ObjectId journalId,
			@RequestBody Journal newJournalEntity) {
		Journal journal = journalService.updateJournal(journalId, newJournalEntity);

		if (journal != null) {
			return new ResponseEntity<>(journal, HttpStatus.OK);
		}
		return new ResponseEntity<>(new ApiResponse("Journal is no longer available", false), HttpStatus.BAD_REQUEST);

	}

}
