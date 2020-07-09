package com.sms.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.dtos.StudentApplicationData;
import com.sms.dtos.StudentDTO;
import com.sms.dtos.StudentFeePaymentDTO;
import com.sms.services.StudentService;

@RestController
@RequestMapping("/sms")
public class StudentController {
	private static final Logger lg = org.slf4j.LoggerFactory.getLogger(StudentController.class);
	private StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping(value ="/api/students/register")
	public ResponseEntity<?> apply(@RequestBody StudentApplicationData applicationData) {
		studentService.apply(applicationData);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@PatchMapping("/ac/ad/api/students/accept/{studentSN}")
	public ResponseEntity<?> accept(@PathVariable int studentSN) {
		studentService.admit(studentSN);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping("/ac/ad/api/students/dismiss/{studentSN}")
	public ResponseEntity<?> dismiss(@PathVariable int studentSN) {
		studentService.dismiss(studentSN);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/ac/ad/api/students/get_all/admitted")
	public ResponseEntity<List<StudentDTO>> getAllAdmittedStudents() {
		lg.warn("Out of my(get admitted students) hands, now in the hands of students service !!!");
		return new ResponseEntity<>(studentService.getAdmitted(), HttpStatus.OK);
	}
	
	@GetMapping("/ac/ad/api/students/get_all/applied")
	public ResponseEntity<List<StudentDTO>> getNonAdmittedStudents() {
		lg.warn("Out of my hands, now in the hands of students service !!!");
		return new ResponseEntity<>(studentService.getNonAdmitted(), HttpStatus.OK);
	}
}
