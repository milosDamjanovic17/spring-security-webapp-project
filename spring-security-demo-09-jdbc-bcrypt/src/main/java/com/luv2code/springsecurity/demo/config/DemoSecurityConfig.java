package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// add/inject a reference to our security data source, NASA VARIJABLA (u klasi DemoAppConfig) U KOJOJ SMO DEFINISALI CONNECTION POOL
	@Autowired
	private DataSource securityDataSource; // reminder: imamo ComponentScan definisan, securityDataSource je Bean u DemoAppConfig, tako da ce biti ucitan, ne mora da se pravi objekat
	
	@Override // citanje podataka iz DB
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// use jdbc authentication we created (securityDataSource)
		auth.jdbcAuthentication().dataSource(securityDataSource); // govorimo Springu da cita podatke iz nase DB preko securityDataSource
		
		
			// BARE IN MIND DA U BAZI AUTHORITY == ROLE !!!
		
			// ENCRYPTED bcrypt PASS KOJI SMO KORISTILI U DB spring_security_demo_bcrypt  JE GENERISAN ZA password: fun123
		
	}	
	
	@Override // ovom klasom cemo mapirati nasu custom login formu, ovim smo dali referencu Springu da koristi nasu Custom login formu
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests() // Restrict access base on HttpServletRequest
				.antMatchers("/").hasRole("EMPLOYEE") // ONAJ KO JE EMPLOYEE CE BITI PREUSMEREN NA "/" STRANU
				.antMatchers("/leaders/**").hasRole("MANAGER") // ONAJ KO JE MANAGER CE BITI PREUSMEREN NA "/leaders" STRANE I SUBDIREKTORIJUME POD /leaders/**
				.antMatchers("/systems/**").hasRole("ADMIN") // ONAJ CIJI JE ROLE ADMIN CE BITI PREUSMEREN NA "/systems" STRANE I SUBDIREKTORIJUME POD /system/**
			.and()
			.formLogin() // formLogin customization
				.loginPage("/showMyLoginPage") // path to show our custom login form at the request mapping, fakticki redirect na nasu custom login stranu LoginControlleru
				.loginProcessingUrl("/authenticateTheUser") // login form should POST data to this URL for processing, ovo ce nam biti referenca za form action = "" u JSP strani
				.permitAll() // allow everyone to see the login page
				.and()
				.logout().permitAll() // adds logout support
				.and()
				.exceptionHandling().accessDeniedPage("/access-denied"); // OVIM SE MAPIRA PATH NASE CUSTOM PERMISSION ACCESS DENIED STRANE
	}

	
	
}
