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
	
	@GetMapping("/stud-side-nav")
	public String studentSideNav() {
		return "up/stud-side-nav";
	}
	
	@GetMapping("/tstaff-side-nav")
	public String staffSideNav() {
		return "teachers/tstaff-side-nav";
	}
	
	@GetMapping(value = {"/", "", "/home"})
	public String home() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "up/login";
	}
    @GetMapping("/academic/admin/student_reports")
	public String studentReports() {
		return "student";
	}
    
    @GetMapping("/academic/admin/staff_reports")
	public String staffReports() {
		return "staff";
	}
    
    @GetMapping("/academic/student/fee/pay")
   	public String payFee() {
   		return "up/pay_fees";
   	}
    
    @GetMapping("/academic/add_student")
	public String addStudent() {
		return "add student";
	}
    
    @GetMapping("/academic/admin/add_staff")
   	public String addStaff() {
   		return "add staff";
   	}
    
    @GetMapping("/academic/admin/add_subject")
   	public String addSubject() {
   		return "add subject";
   	}
    
    @GetMapping("/academic/admin/add_class")
   	public String addClass() {
   		return "add class";
   	}
    
    //register marks for students
    @GetMapping("/academic/teachers/student/marks")
    public String registerStudentMarks() {
    	return "teachers/register marks";
    }
    
    //get student's results
    @GetMapping("/academic/student/results")
    public String getStudentResults() {
    	return "up/view_results";
    }
    
    @GetMapping("/academic/student/profile")
    public String getstudentProfile() {
    	return "up/profile";
    }
    
    @GetMapping("/academic/teacher/profile")
    public String getTeacherProfile() {
    	return "teachers/profile";
    }
    
    @GetMapping("/academic/admin/profile")
    public String getAdminProfile() {
    	return "profile";
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
    
    @GetMapping("/welcome")
    public String welcome() {
    	return "welcome";
    }
}
