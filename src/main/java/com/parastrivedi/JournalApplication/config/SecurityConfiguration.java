package com.parastrivedi.JournalApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
	
	{
		http
		.csrf(csrf->csrf.disable())
		.cors(cors->cors.disable())
		.authorizeHttpRequests(request->request
				.requestMatchers("/user/**").permitAll()
				.requestMatchers("/user/**").hasRole("user").anyRequest().authenticated());
//		.formLogin(form->form.permitAll());
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
			return userDetailsServiceImpl;
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user = User.builder().username("shyaram")
//				.password(passwordEncoder().encode("shyaram")).build();
//		return new InMemoryUserDetailsManager(user);
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsServiceImpl);
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}
}
