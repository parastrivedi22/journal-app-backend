package com.parastrivedi.JournalApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.parastrivedi.JournalApplication.exception.ResourceNotFoundException;
import com.parastrivedi.JournalApplication.repositry.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userEmail)  {
		log.info("{} called loadUserByUsername", UserDetailsServiceImpl.class);		

		com.parastrivedi.JournalApplication.entity.User userEntity =
				userRepository.findByUserEmail(userEmail)
				.orElseThrow(() -> new ResourceNotFoundException("User", userEmail));
		
		if (userEntity != null) {
			UserDetails user = 
					User.builder()
					.username(userEntity.getUserEmail())
					.password(userEntity.getPassword())
					.roles(userEntity.getRole())
					.build();
			return user;
		} else {
			throw new UsernameNotFoundException(userEmail);
		}
	}

}
