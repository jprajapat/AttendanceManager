package com.attendance.manager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests()
		.antMatchers("/employee/saveEmployee","/employee/updateEmployee/{id}","/employee/deleteEmployee/{id}").hasRole("ADMIN")
		.antMatchers("/employee/getAllEmployee","/employee/getEmployeeByName/{name}","/employee/getEmployeeStartingName/{name}").hasAnyRole("ADMIN","NORMAL")
		.antMatchers("/attendance/saveAttendance","/attendance/updateAttendance/{id}","/attendance/deleteAttendance/{id}").hasRole("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser("employee").password("123").roles("NORMAL");
		auth.inMemoryAuthentication().withUser("jitendra").password("123").roles("ADMIN");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();	
	}

}
