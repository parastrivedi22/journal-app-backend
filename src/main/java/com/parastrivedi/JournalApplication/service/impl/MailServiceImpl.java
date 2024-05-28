package com.parastrivedi.JournalApplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.parastrivedi.JournalApplication.service.MailService;

public class MailServiceImpl implements MailService
{
	
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public boolean sendEmail(String to, String subject, String content) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

}
