package com.parastrivedi.JournalApplication.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parastrivedi.JournalApplication.entity.Journal;
import com.parastrivedi.JournalApplication.exception.ApiResponse;
import com.parastrivedi.JournalApplication.exception.ResourceNotFoundException;
import com.parastrivedi.JournalApplication.repositry.JournalRepository;
import com.parastrivedi.JournalApplication.service.JournalService;

@Service
public class JournalServiceImpl implements JournalService {

	@Autowired
	private JournalRepository journalRepositry;

	@Override
	public List<Journal> getAll() {
		// TODO Auto-generated method stub
		return journalRepositry.findAll();

	}

	@Override
	public Journal getById(ObjectId id) {
		// TODO Auto-generated method stub
		return journalRepositry.findById(id).orElseThrow(()->new ResourceNotFoundException("journal", id));
		
	}

	@Override
	public ApiResponse deleteById(ObjectId id) {
		// TODO Auto-generated method stub
			journalRepositry.findById(id).orElseThrow(()->new ResourceNotFoundException("journal", id));
			journalRepositry.deleteById(id);
		   return new ApiResponse("Journal deleted", true);
	}

	@Override
	public Journal createJournal(Journal journal) {
		// TODO Auto-generated method stub
		journal.setCreatedAt(LocalDateTime.now());
		return journalRepositry.save(journal);
	}

	public Journal updateJournal(ObjectId id, Journal newJournal) {

		Journal journal = journalRepositry.findById(id).orElseThrow(()->new ResourceNotFoundException("journal", id));

		journal.setTitle(newJournal.getTitle() == null || newJournal.getTitle().trim().equals("") ? journal.getTitle()
				: newJournal.getTitle().trim());

		journal.setContent(
				newJournal.getContent() == null || newJournal.getContent().trim().equals("") ? journal.getContent()
						: newJournal.getContent().trim());

		Journal updatedJournal = journalRepositry.save(journal);
		return updatedJournal;
	}

}
