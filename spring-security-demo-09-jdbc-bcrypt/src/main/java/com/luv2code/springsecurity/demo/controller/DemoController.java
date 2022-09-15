package com.luv2code.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/") // Mapirano na home page, path spring-security-demo/ , fakticki "/" == HOME/INDEX PAGE !!!
	public String showHome() {
		
		return "home"; // u DemoAppConfig klasi je podesen viewResolver prefix i suffix, tako da je ovaj return fakticki /WEB-INF/view/home.jsp
	}
	
	
	
	// add request mapping for /leaders
	@GetMapping("/leaders")
	public String showLeaders() {
		
		return "leaders";
	}
	
	// add request mapping for /systems
	@GetMapping("/systems")
	public String showSystems() {
		
		return "systems";
	}
	
}
