package com.parastrivedi.JournalApplication.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Journal>> getAllJournalEntriesOfUser(){
		return new ResponseEntity<>(journalService.getAll(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Journal> getJournalById(@PathVariable ObjectId id){
		return new ResponseEntity<>(journalService.getById(id),HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<Journal> createJournalEntry(@RequestBody Journal journal){
		return new ResponseEntity<>(journalService.createJournal(journal), HttpStatus.CREATED);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteJournalEntry(@PathVariable ObjectId id){
		return new ResponseEntity<>(journalService.deleteById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Journal> updateJournalEntity(@PathVariable ObjectId id, @RequestBody Journal newJournalEntity){
		
		return new ResponseEntity<>(journalService.updateJournal(id, newJournalEntity), HttpStatus.OK);
		
	}

}
