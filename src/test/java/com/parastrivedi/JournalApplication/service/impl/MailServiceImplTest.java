package com.parastrivedi.JournalApplication.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.parastrivedi.JournalApplication.exception.BadRequestException;
import com.parastrivedi.JournalApplication.service.MailService;

@SpringBootTest
public class MailServiceImplTest {

	@Autowired
	private MailService service;

	@Test
	public void mailTest1() {
		assertThrows(BadRequestException.class, () -> service.sendEmail(null, null, null));

	}
	@Test
	public void mailTest2() {
		assertThrows(BadRequestException.class, () -> service.sendEmail("ptrivedi288gmail.com", "Hello", "Hey"));
		
	}
	
}
