package com.fileupload.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class InMemory {

	@Bean
	public UserDetailsManager inMemoryUserDetailsManager() {
		
		UserDetails user1 = User.builder().username("user1").password("{noop}password").build();
		UserDetails user2 = User.builder().username("user2").password("{noop}password").build();
		
		return new InMemoryUserDetailsManager(user1, user2);
	}
	
}
