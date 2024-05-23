package com.parastrivedi.JournalApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
        .csrf(c->c.disable())
        .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/public/**").permitAll()
            .requestMatchers("/user/**", "/journal/**").hasRole("user").anyRequest().authenticated()// Public endpoints
           
        )
//        .httpBasic();// Basic authentication
		.httpBasic(Customizer.withDefaults());// Basic authentication

		
//		http
//		.csrf(c->c.disable())
//		.cors(cors->cors.disable())
//		.authorizeHttpRequests(request->request
//				.requestMatchers("/public/**").permitAll()
//				.requestMatchers("/user/**", "/journal/**").hasRole("user").anyRequest().authenticated())
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		
//			
//		.formLogin(form->form.permitAll());
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
			return userDetailsServiceImpl;
	}
	
	
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
