package com.parastrivedi.JournalApplication.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import com.parastrivedi.JournalApplication.service.MailService;

@SpringBootTest
public class MailServiceImplTest {

	@Autowired
	private MailService service;
	

	@Test
	public void mailTest() {
		boolean sendEmail = service.sendEmail("ptrivedi288@gmail.com", "Email from Spring Boot", "this is a test email from spring boot");
		assertTrue(sendEmail);
		
	}
}


