package com.parastrivedi.JournalApplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.parastrivedi.JournalApplication.service.MailService;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class MailServiceImpl implements MailService
{
	
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public boolean sendEmail(String to, String subject, String text) {
		// TODO Auto-generated method stub
		
		try {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(to);
			mail.setSubject(subject);
			mail.setText(text);
			mailSender.send(mail);
			return true;
		}catch(Exception ex) {
			log.error("{} : email has been not sent",MailServiceImpl.class );
			System.out.print(ex);
		}
		
		
		return false;
	}

	
	

}
