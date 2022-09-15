package com.luv2code.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/showMyLoginPage") // path se mora poklopiti sa .loginPage("") unutar DemoSecurityConfig -> configure(HttpSecurity) metode
	public String showMyLoginPage(){
		
		
		return "fancy-login"; // view name, nasa custom login JSP strana
	}
	
	// add request mapping for custom Access Denied page "/access-denied"
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		
		
		
		return "access-denied";
	}
	
	
	
	
}
