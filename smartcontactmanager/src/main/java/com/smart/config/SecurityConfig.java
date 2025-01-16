package com.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
		.authorizeHttpRequests(auth->{
			auth
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/user/**").hasRole("USER")
				.requestMatchers("/**").permitAll();
	})
		.formLogin()
		.loginPage("/signin")
		.and()
		.csrf().disable();
		
		
		
		
		return httpSecurity.build();
	}
}
