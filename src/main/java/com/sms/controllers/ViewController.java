package com.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sms")
public class ViewController {
	
	//fragments
	@GetMapping("/side-nav")
	public String sideNav() {
		return "side-nav";
	}
	@GetMapping(value = {"/", "", "/home"})
	public String home() {
		return "index";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
    @GetMapping("/ac/ad/student")
	public String student() {
		return "student";
	}
    @GetMapping("/staff")
	public String staff() {
		return "staff";
	}
    @GetMapping("/result")
	public String result() {
		return "result";
	}
    @GetMapping("/report")
	public String report() {
		return "report";
	}
    @GetMapping("/fee")
	public String fee() {
		return "fee";
	}
    
    
    @GetMapping("/add_student")
	public String addStudent() {
		return "add student";
	}
    @GetMapping("/add_staff")
   	public String addStaff() {
   		return "add staff";
   	}
}
