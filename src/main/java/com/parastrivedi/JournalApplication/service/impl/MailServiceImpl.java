package com.parastrivedi.JournalApplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.parastrivedi.JournalApplication.exception.BadRequestException;
import com.parastrivedi.JournalApplication.service.MailService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MailServiceImpl implements MailService {

	public final String EMAIL_PATTERN = "(?i)[-a-zA-Z0-9+_][-a-zA-Z0-9+_.]*@[-a-zA-Z0-9][-a-zA-Z0-9.]*\\.[a-zA-Z]{2,30}";

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public boolean sendEmail(String to, String subject, String text) {
		// TODO Auto-generated method stub

		if (to == null || to.trim().equals("") || subject == null || subject.trim().equals("") || text == null
				|| text.trim().equals("")) {
			log.warn("{} : email content is invalid", MailServiceImpl.class);
			throw new BadRequestException("email content is invalid");
		}

		if (!to.matches(EMAIL_PATTERN)) {
			log.warn("{} : email is invalid", MailServiceImpl.class);
			throw new BadRequestException("email is invalid");
		}

		try {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(to);
			mail.setSubject(subject);
			mail.setText(text);
			mailSender.send(mail);
			return true;
		} catch (Exception ex) {
			log.warn("{} : email has been not sent", MailServiceImpl.class);
			System.out.print(ex);
		}

		return false;
	}

}
